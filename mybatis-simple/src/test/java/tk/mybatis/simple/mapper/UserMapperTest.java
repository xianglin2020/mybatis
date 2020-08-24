package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

import java.util.*;

public class UserMapperTest extends BaseMapperTest {

    @Test
    public void selectById() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser sysUser = mapper.selectById(1L);
            Assert.assertNotNull(sysUser);
            Assert.assertEquals("admin", sysUser.getUserName());
        }
    }

    @Test
    public void selectAll() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> list = mapper.selectAll();
            Assert.assertNotNull(list);
            Assert.assertFalse(list.isEmpty());
        }
    }

    @Test
    public void selectRolesByUserID() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> list = mapper.selectRolesByUserId(1L);
            Assert.assertNotNull(list);
            Assert.assertFalse(list.isEmpty());
        }
    }

    @Test
    public void insert() {
        SysUser user = new SysUser();
        user.setUserName("test1");
        user.setUserPassword("123456");
        user.setUserEmail("test@mail.com");
        user.setUserInfo("test info ");
        user.setHeadImg(new byte[]{1, 2, 3});
        user.setCreateTime(new Date());
        SysUser user1 = new SysUser();
        user1.setUserName("test1");
        user1.setUserPassword("123456");
        user1.setUserEmail("test@mail.com");
        user1.setUserInfo("test info ");
        user1.setHeadImg(new byte[]{1, 2, 3});
        user1.setCreateTime(new Date());
        // sqlSessionFactory.openSession() 默认是不自动提交的
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int insert = mapper.insert(user);
            int insert1 = mapper.insert2(user1);
            Assert.assertEquals(1, insert);
            Assert.assertEquals(1, insert1);
            Assert.assertNull(user.getId());
            Assert.assertNotNull(user1.getId());
        }
    }

    @Test
    public void updateById() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = mapper.selectById(1L);
            user.setUserName("modified name");
            int update = mapper.updateById(user);

            user = mapper.selectById(1L);

            Assert.assertEquals(1, update);
            Assert.assertEquals("modified name", user.getUserName());
        }
    }

    @Test
    public void deleteById() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            // 查询用户
            SysUser user = mapper.selectById(1L);
            Assert.assertNotNull(user);
            // 删除用户
            int delete = mapper.deleteById(user.getId());
            Assert.assertEquals(1, delete);
            // 查询用户
            SysUser user1 = mapper.selectById(user.getId());
            Assert.assertNull(user1);
        }
    }

    @Test
    public void selectRolesByUserIdAndRoleEnabled() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> list = mapper.selectRolesByUserIdAndRoleEnabled(1L, 1);
            Assert.assertNotNull(list);
            Assert.assertFalse(list.isEmpty());
        }
    }

    @Test
    public void selectByUser() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser user1 = new SysUser();
            user1.setUserName("ad");
            List<SysUser> list1 = mapper.selectByUser(user1);
            List<SysUser> list1_1 = mapper.selectByUserWithTrim(user1);
            Assert.assertFalse(list1.isEmpty());
            Assert.assertFalse(list1_1.isEmpty());

            SysUser user2 = new SysUser();
            user2.setUserEmail("test@mybatis.tk");
            List<SysUser> list2 = mapper.selectByUser(user2);
            List<SysUser> list2_1 = mapper.selectByUserWithTrim(user2);
            Assert.assertFalse(list2.isEmpty());
            Assert.assertFalse(list2_1.isEmpty());

            SysUser user3 = new SysUser();
            user3.setUserName("ad");
            user3.setUserEmail("test@mybatis.tk");
            List<SysUser> list3 = mapper.selectByUser(user3);
            List<SysUser> list3_1 = mapper.selectByUserWithTrim(user3);
            Assert.assertTrue(list3.isEmpty());
            Assert.assertTrue(list3_1.isEmpty());
        }
    }

    @Test
    public void updateByIdSelective() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setId(1L);
            user.setUserEmail("test@mybatis.tk");
            int update = mapper.updateByIdSelective(user);
            Assert.assertEquals(1, update);

            SysUser user1 = mapper.selectById(1L);
            Assert.assertEquals("admin", user1.getUserName());
            Assert.assertEquals("test@mybatis.tk", user1.getUserEmail());
        }
    }

    @Test
    public void insertSelective() {
        SysUser user = new SysUser();
        user.setUserName("test-selective");
        user.setUserPassword("123456");
        user.setUserInfo("test info ");
        user.setHeadImg(new byte[]{1, 2, 3});
        user.setCreateTime(new Date());
        // sqlSessionFactory.openSession() 默认是不自动提交的
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int insert2 = mapper.insert2(user);
            Assert.assertEquals(1, insert2);

            SysUser user1 = mapper.selectById(user.getId());
            Assert.assertEquals("tk@mybatis.tk", user1.getUserEmail());
        }
    }

    @Test
    public void selectUserByIdOrUserName() {
        SysUser user = new SysUser();
        user.setId(1L);
        user.setUserName("admin");

        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser user1 = mapper.selectUserByIdOrUserName(user);
            Assert.assertNotNull(user1);

            user.setId(null);
            SysUser user2 = mapper.selectUserByIdOrUserName(user);
            Assert.assertNotNull(user2);

            user.setUserName(null);
            SysUser user3 = mapper.selectUserByIdOrUserName(user);
            Assert.assertNull(user3);
        }
    }

    @Test
    public void selectByIdList() {
        List<Long> list = Arrays.asList(1L, 2L, 3L);
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> userList = mapper.selectByIdList(list);
            Assert.assertFalse(userList.isEmpty());
            Assert.assertEquals(1, userList.size());
        }
    }

    @Test
    public void selectByIdArray() {
        Long[] arr = new Long[]{1L, 2L, 3L};
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> userList = mapper.selectByIdArray(arr);
            Assert.assertFalse(userList.isEmpty());
            Assert.assertEquals(1, userList.size());
        }
    }

    @Test
    public void selectByIdMap() {
        Map<String, Long> map = new HashMap<>();
        map.put("id1", 1L);
        map.put("id2", 2L);
        map.put("id3", 3L);
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> userList = mapper.selectByIdMap(map);
            Assert.assertFalse(userList.isEmpty());
            Assert.assertEquals(1, userList.size());
        }
    }


    @Test
    public void insertList() {
        List<SysUser> list = new ArrayList<>();
        SysUser user;
        for (int i = 0; i < 3; i++) {
            user = new SysUser();
            user.setUserName("test" + i);
            user.setUserPassword("123456");
            user.setUserEmail("test@mybatis.tk");
            user.setCreateTime(new Date());
            list.add(user);
        }
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int insertList = mapper.insertList(list);
            Assert.assertEquals(list.size(), insertList);

            list.forEach(user1 -> System.out.println(user1.getId()));
        }
    }

    @Test
    public void updateByMap() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            Map<String, Object> map = new HashMap<>();
            map.put("id", 1L);
            map.put("user_name", "userName");
            map.put("user_email", "email");
            mapper.updateByMap(map);
            SysUser user = mapper.selectById(1L);
            Assert.assertEquals("userName", user.getUserName());
            Assert.assertEquals("email", user.getUserEmail());
        }
    }

    @Test
    public void selectUserAndRoleById() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = mapper.selectUserAndRoleById(1001L);
            SysUser user2 = mapper.selectUserAndRoleById2(1001L);
            Assert.assertNotNull(user);
            Assert.assertNotNull(user.getRole());
            Assert.assertNotNull(user2);
            Assert.assertNotNull(user2.getRole());
        }
    }

    @Test
    public void selectUserAndRoleByIdSelect() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = mapper.selectUserAndRoleByIdSelect(1001L);
            Assert.assertNotNull(user);
            Assert.assertNotNull(user.getRole());
        }
    }

    @Test
    public void selectAllUserAndRoles() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> list = mapper.selectAllUserAndRoles();

            Assert.assertFalse(list.isEmpty());
            Assert.assertFalse(list.get(0).getRoleList().isEmpty());
            List<SysUser> list1 = mapper.selectAllUserAndRoleAndPrivileges();
            Assert.assertFalse(list1.isEmpty());
            Assert.assertFalse(list1.get(0).getRoleList().isEmpty());
        }
    }


    @Test
    public void selectAllUserAndRolesSelect() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = mapper.selectAllUserAndRolesSelect(1L);
            Assert.assertNotNull(user);
            System.out.println("获取RoleList");
            Assert.assertNotNull(user.getRoleList());
            Assert.assertFalse(user.getRoleList().isEmpty());
            System.out.println("获取PrivilegeList");
            Assert.assertNotNull(user.getRoleList().get(0).getPrivilegeList());
            Assert.assertFalse(user.getRoleList().get(0).getPrivilegeList().isEmpty());
        }
    }

    @Test
    public void selectUserById() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setId(1L);
            mapper.selectUserById(user);
            Assert.assertNotNull(user.getUserName());
        }
    }
}