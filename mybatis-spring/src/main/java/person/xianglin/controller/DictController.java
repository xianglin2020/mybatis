package person.xianglin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import person.xianglin.model.SysDict;
import person.xianglin.service.DictService;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = "/dicts")
public class DictController {
    private final DictService dictService;

    public DictController(DictService dictService) {
        this.dictService = dictService;
    }

    @RequestMapping
    public ModelAndView dicts(@ModelAttribute ModelAndView modelAndView, SysDict sysDict, Integer offset, Integer limit) {
        modelAndView.setViewName("dicts");
        List<SysDict> dictList = dictService.findBySysDict(sysDict, offset, limit);
        modelAndView.addObject("dicts", dictList);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(@ModelAttribute ModelAndView modelAndView, Long id) {
        SysDict sysDict;
        if (Objects.isNull(id)) {
            sysDict = new SysDict();
        } else {
            sysDict = dictService.findById(id);
        }
        modelAndView.addObject("model", sysDict);
        modelAndView.setViewName("dict_add");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute ModelAndView modelAndView, SysDict sysDict) {
        try {
            dictService.saveOrUpdate(sysDict);
            modelAndView.setViewName("redirect:/dicts");
        } catch (Exception e) {
            modelAndView.setViewName("dict_add");
            modelAndView.addObject("model", sysDict);
            modelAndView.addObject("msg", e.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public ModelMap delete(@ModelAttribute ModelMap modelMap, @RequestParam Long id) {
        try {
            boolean success = dictService.deleteById(id);
            modelMap.put("success", success);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("msg", e.getMessage());
        }
        return modelMap;
    }

}
