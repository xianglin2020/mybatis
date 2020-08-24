package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

public class CacheTest extends BaseMapperTest {

    @Test
    public void testL1Cache() {
        SysUser user1;
        try (SqlSession sqlSession = getSqlSession()) {
            // 一级缓存和SqlSession绑定
            // org.apache.ibatis.binding.MapperProxy.methodCache
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            user1 = userMapper.selectById(1L);
            user1.setUserName("New Name");
            SysUser user2 = userMapper.selectById(1L);
            Assert.assertEquals("New Name", user2.getUserName());
            Assert.assertEquals(user1, user2);
        }

        System.out.println("开启新的SqlSession");
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 任何INSERT、UPDATE、DELETE操作都会清空一级缓存
            SysUser user = userMapper.selectById(1L);
            Assert.assertNotEquals(user, user1);
            userMapper.deleteById(2L);
            SysUser user2 = userMapper.selectById(1L);
            Assert.assertNotEquals(user, user2);
        }
    }

    @Test
    public void testL2Cache() {
        SqlSession sqlSession = getSqlSession();
        SysRole sysRole1;
        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            sysRole1 = mapper.selectById(1L);
            sysRole1.setRoleName("NEW NAME");
            SysRole sysRole2 = mapper.selectById(1L);
            Assert.assertEquals("NEW NAME", sysRole2.getRoleName());
            Assert.assertEquals(sysRole1, sysRole2);
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }

        sqlSession = getSqlSession();
        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole2 = mapper.selectById(1L);
            Assert.assertEquals(sysRole2.getRoleName(), "NEW NAME");
            Assert.assertNotEquals(sysRole2, sysRole1);
            SysRole sysRole3 = mapper.selectById(1L);
            Assert.assertNotEquals(sysRole2, sysRole3);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testDirtyData() {
        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        SysUser sysUser = mapper.selectUserAndRoleById(1001L);
        Assert.assertEquals("普通用户", sysUser.getRole().getRoleName());
        sqlSession.close();

        SqlSession sqlSession1 = getSqlSession();
        RoleMapper mapper1 = sqlSession1.getMapper(RoleMapper.class);
        SysRole sysRole = mapper1.selectById(2L);
        sysRole.setRoleName("脏数据");
        mapper1.updateById(sysRole);
        sqlSession1.commit();
        sqlSession1.close();

        SqlSession sqlSession2 = getSqlSession();
        UserMapper userMapper = sqlSession2.getMapper(UserMapper.class);
        RoleMapper roleMapper = sqlSession2.getMapper(RoleMapper.class);
        SysUser sysUser1 = userMapper.selectUserAndRoleById(1001L);
        SysRole sysRole1 = roleMapper.selectById(2L);
        Assert.assertNotEquals("普通用户", sysUser1.getRole().getRoleName());
        Assert.assertEquals("脏数据", sysRole1.getRoleName());

        sysRole1.setRoleName("普通用户");
        roleMapper.updateById(sysRole1);
        sqlSession2.commit();
        sqlSession2.close();
    }
}
