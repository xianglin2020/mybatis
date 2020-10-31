package person.xianglin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import person.xianglin.mapper.ProductMapper;
import person.xianglin.model.Product;
import person.xianglin.service.IProductService;

import java.util.List;

/**
 * @author xianglin
 */
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> listProduct(int pageSize, int pageNum) {
        return productMapper.listAll(pageSize, pageNum);
    }
}
