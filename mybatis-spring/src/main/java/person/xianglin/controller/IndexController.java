package person.xianglin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * @author xianglin
 */
@Controller
public class IndexController {

    @GetMapping(value = {"/index", ""})
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("now", new Date());
        return mav;
    }
}
