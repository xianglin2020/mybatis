package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.Enabled;
import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.model.SysRole;

import java.util.Date;
import java.util.List;

public class RoleMapperTest extends BaseMapperTest {

    @Test
    public void selectById() {
        try (SqlSession sqlSession = getSqlSession()) {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = mapper.selectById(1L);
            Assert.assertNotNull(sysRole);
            System.out.println(sysRole);
        }
    }

    @Test
    public void selectById1() {
        try (SqlSession sqlSession = getSqlSession()) {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = mapper.selectById2(1L);
            Assert.assertNotNull(sysRole);
            System.out.println(sysRole);
        }
    }

    @Test
    public void selectById2() {
        try (SqlSession sqlSession = getSqlSession()) {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = mapper.selectById3(1L);
            Assert.assertNotNull(sysRole);
            System.out.println(sysRole);
        }
    }

    @Test
    public void selectAll() {
        try (SqlSession sqlSession = getSqlSession()) {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> list = mapper.selectAll();
            Assert.assertNotNull(list);
            Assert.assertFalse(list.isEmpty());
        }
    }

    @Test
    public void insert() {
        try (SqlSession sqlSession = getSqlSession()) {
            SysRole role = new SysRole();
            role.setId(3L);
            role.setCreateBy(1L);
            role.setCreateTime(new Date());
            role.setEnabled(Enabled.enabled);
            role.setRoleName("测试角色");

            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            int insert = mapper.insert(role);
            Assert.assertEquals(1, insert);
        }
    }

    @Test
    public void insert2() {
        try (SqlSession sqlSession = getSqlSession()) {
            SysRole role = new SysRole();
            role.setCreateBy(1L);
            role.setCreateTime(new Date());
            role.setEnabled(Enabled.enabled);
            role.setRoleName("测试角色");

            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            int insert2 = mapper.insert2(role);
            Assert.assertEquals(1, insert2);
            Assert.assertNotNull(role.getId());
        }
    }

    @Test
    public void insert3() {
        try (SqlSession sqlSession = getSqlSession()) {
            SysRole role = new SysRole();
            role.setCreateBy(1L);
            role.setCreateTime(new Date());
            role.setEnabled(Enabled.enabled);
            role.setRoleName("测试角色");

            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            int insert3 = mapper.insert3(role);
            Assert.assertEquals(1, insert3);
            Assert.assertNotNull(role.getId());
        }
    }

    @Test
    public void updateAndDel() {
        SysRole role = new SysRole();
        role.setId(1L);
        role.setRoleName("管理员");
        role.setCreateBy(2L);
        role.setEnabled(Enabled.disabled);
        try (SqlSession sqlSession = getSqlSession()) {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            int update = mapper.updateById(role);
            Assert.assertEquals(1, update);

            SysRole sysRole = mapper.selectById(1L);
            Assert.assertEquals(sysRole.getCreateBy().intValue(), 2);

            int delete = mapper.deleteById(1L);
            SysRole sysRole1 = mapper.selectById(1L);

            Assert.assertEquals(1, delete);
            Assert.assertNull(sysRole1);
        }
    }

    @Test
    public void selectRoleByUserId() {
        try (SqlSession sqlSession = getSqlSession()) {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> list = mapper.selectRoleByUserId(1L);
            Assert.assertFalse(list.isEmpty());
        }
    }

    @Test
    public void selectRoleByUserIdChoose() {
        try (SqlSession sqlSession = getSqlSession()) {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = roleMapper.selectById(2L);
            sysRole.setEnabled(Enabled.disabled);
            roleMapper.updateById(sysRole);

            List<SysRole> list = roleMapper.selectRoleByUserIdChoose(1L);
            Assert.assertEquals(2, list.size());
            Assert.assertNotNull(list.get(0).getPrivilegeList());
            Assert.assertNull(list.get(1).getPrivilegeList());
        }
    }

    @Test
    public void testUpdateById() {
        SqlSession sqlSession = getSqlSession();
        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        try {
            SysRole sysRole = mapper.selectById(2L);
            Assert.assertEquals(sysRole.getEnabled(), Enabled.disabled);
            sysRole.setEnabled(Enabled.enabled);
            mapper.updateById(sysRole);
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }

    }
}