package person.xianglin.mybatisspringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import person.xianglin.mybatisspringboot.service.IUserService;
import person.xianglin.simple.model.SysUser;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xianglin
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Resource
    private IUserService userService;

    @GetMapping("/{id}")
    public SysUser user(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<SysUser> users() {
        return userService.listUsers();
    }
}
