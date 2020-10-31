package person.xianglin.mybatisspringboot.service;


import person.xianglin.mybatisspringboot.model.Product;

import java.util.List;

/**
 * @author xianglin
 */
public interface IProductService {
    List<Product> listProduct(int pageSize, int pageNum);
}
