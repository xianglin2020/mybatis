package person.xianglin.mapper;

import person.xianglin.model.Product;

import java.util.List;

/**
 * @author xianglin
 */
public interface ProductMapper {
    List<Product> listAll(int pageSize, int pageNum);
}
