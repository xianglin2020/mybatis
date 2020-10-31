package person.xianglin.mybatisspringboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import person.xianglin.mybatisspringboot.mapper.ProductMapper;
import person.xianglin.mybatisspringboot.model.Product;
import person.xianglin.mybatisspringboot.service.IProductService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xianglin
 */
@Service
public class ProductServiceImpl implements IProductService {
    @Resource
    private ProductMapper productMapper;

    @Override
    public List<Product> listProduct(int pageSize, int pageNum) {
        return productMapper.listAll(pageSize, pageNum);
    }
}
