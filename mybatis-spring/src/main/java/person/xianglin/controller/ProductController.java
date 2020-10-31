package person.xianglin.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import person.xianglin.model.Product;
import person.xianglin.service.IProductService;

import java.util.List;

/**
 * @author xianglin
 */
@Controller
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping(value = "/list/{pageNum}")
    public String listAllProduct(Model model, @PathVariable(value = "pageNum", required = false) int pageNum) {
        List<Product> productList = productService.listProduct(5, pageNum);
        PageInfo<Product> pageInfo = new PageInfo<>(productList);
        model.addAttribute("productList", productList);
        model.addAttribute("pageInfo", pageInfo);
        return "product";
    }
}
