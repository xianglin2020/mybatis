package person.xianglin.service;

import person.xianglin.model.Product;

import java.util.List;

/**
 * @author xianglin
 */
public interface IProductService {
    List<Product> listProduct(int pageSize, int pageNum);
}
