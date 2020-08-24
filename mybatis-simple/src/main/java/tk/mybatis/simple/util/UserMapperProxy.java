package tk.mybatis.simple.util;

import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 创建mapper的代理
 *
 * @param <T>
 */
public class UserMapperProxy<T> implements InvocationHandler {
    private final Class<T> mapperInterface;
    private final SqlSession sqlSession;

    public UserMapperProxy(Class<T> mapperInterface, SqlSession sqlSession) {
        this.mapperInterface = mapperInterface;
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        return sqlSession.selectList(mapperInterface.getCanonicalName() + "." + method.getName());
    }
}
