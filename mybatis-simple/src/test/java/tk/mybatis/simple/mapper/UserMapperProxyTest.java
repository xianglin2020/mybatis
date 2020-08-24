package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import tk.mybatis.simple.model.SysUser;
import tk.mybatis.simple.util.UserMapperProxy;

import java.lang.reflect.Proxy;
import java.util.List;

public class UserMapperProxyTest extends BaseMapperTest {

    @Test
    public void invoke() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapperProxy<UserMapper> proxy = new UserMapperProxy<>(UserMapper.class, sqlSession);
            UserMapper userMapper = (UserMapper) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{UserMapper.class}, proxy);
            List<SysUser> list = userMapper.selectAll();
        }

    }
}