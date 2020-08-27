package person.xianglin.mybatisspringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import person.xianglin.mybatisspringboot.mapper.CountryMapper;

import javax.annotation.Resource;

/**
 * @author xianglin
 */
@SpringBootApplication
@MapperScan(basePackages = {"person.xianglin.simple.mapper", "person.xianglin.mybatisspringboot.mapper"})
public class MybatisSpringbootApplication implements CommandLineRunner {
    @Resource
    private CountryMapper countryMapper;

    public static void main(String[] args) {
        SpringApplication.run(MybatisSpringbootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(countryMapper.listAll());
    }
}
