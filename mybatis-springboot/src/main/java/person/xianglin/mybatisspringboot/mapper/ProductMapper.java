package person.xianglin.mybatisspringboot.mapper;


import org.apache.ibatis.annotations.Mapper;
import person.xianglin.mybatisspringboot.model.Product;

import java.util.List;

/**
 * @author xianglin
 */
@Mapper
public interface ProductMapper {
    List<Product> listAll(int pageSize, int pageNum);
}
