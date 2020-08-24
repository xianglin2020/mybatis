package tk.mybatis.simple.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import tk.mybatis.simple.model.Country;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountryMapperTest {

    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void init() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Country> countryList = sqlSession.selectList("tk.mybatis.simple.mapper.CountryMapper.selectAll");
        printCountryList(countryList);
        // 关闭SqlSession
        sqlSession.close();
    }

    private void printCountryList(List<Country> countryList) {
        for (Country country : countryList) {
            System.out.printf("%-4d%4s%4s\n", country.getId(), country.getCountryname(), country.getCountrycode());
        }
    }

    CountDownLatch latch = new CountDownLatch(5);
    @Test
    public void testRepeat() throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            threadPool.execute(this::asd);
        }
        latch.await();
    }

    private void asd() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        boolean b = sqlSession.selectOne("tk.mybatis.simple.mapper.CountryMapper.check");
        if (b) {
            System.out.println("已存在");
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("name", "name");
            map.put("code", "code");
            int b1 = sqlSession.insert("tk.mybatis.simple.mapper.CountryMapper.insert", map);
            if (b1 == 1) {
                System.out.println("插入成功！");
            }
        }
        sqlSession.commit(true);
        sqlSession.close();
        latch.countDown();
    }
}
