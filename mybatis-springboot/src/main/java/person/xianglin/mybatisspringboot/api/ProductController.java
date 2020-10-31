package person.xianglin.mybatisspringboot.api;

import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import person.xianglin.mybatisspringboot.model.Product;
import person.xianglin.mybatisspringboot.service.IProductService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xianglin
 */
@RestController
@RequestMapping(value = "/prods")
public class ProductController {
    @Resource
    private IProductService productService;

    @GetMapping(value = "/{pageNum}")
    public List<Product> listProduct(@PathVariable(name = "pageNum", required = false) int pageNum) {
        return productService.listProduct(5, pageNum);
    }

    @GetMapping(value = "/{pageSize}/{pageNum}")
    public PageInfo<Product> listProduct(@PathVariable(name = "pageSize", required = false) int pageSize,
                                         @PathVariable(name = "pageNum", required = false) int pageNum) {
        List<Product> listProduct = productService.listProduct(pageSize, pageNum);
        return new PageInfo<>(listProduct);
    }
}
