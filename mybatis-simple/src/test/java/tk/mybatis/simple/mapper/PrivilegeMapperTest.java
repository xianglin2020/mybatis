package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysPrivilege;

import java.util.List;

public class PrivilegeMapperTest extends BaseMapperTest {

    @Test
    public void selectById() {
        try (SqlSession sqlSession = getSqlSession()) {
            PrivilegeMapper mapper = sqlSession.getMapper(PrivilegeMapper.class);
            SysPrivilege privilege = mapper.selectById(1L);
            Assert.assertNotNull(privilege);
            Assert.assertNotNull(privilege.getId());
        }
    }

    @Test
    public void selectPrivilegeByRoleId() {
        try (SqlSession sqlSession = getSqlSession()) {
            PrivilegeMapper mapper = sqlSession.getMapper(PrivilegeMapper.class);
            List<SysPrivilege> list = mapper.selectPrivilegeByRoleId(1L);
            Assert.assertFalse(list.isEmpty());
        }
    }
}