package person.xianglin.mybatisspringboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import person.xianglin.mybatisspringboot.model.Country;

import java.util.List;

/**
 * @author xianglin
 */
@Mapper
public interface CountryMapper {
    /**
     * 查询全部数据
     *
     * @return 集合
     */
    List<Country> listAll();
}
