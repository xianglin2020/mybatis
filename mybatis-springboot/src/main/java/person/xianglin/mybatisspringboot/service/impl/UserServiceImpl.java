package person.xianglin.mybatisspringboot.service.impl;

import org.springframework.stereotype.Service;
import person.xianglin.mybatisspringboot.service.IUserService;
import person.xianglin.simple.mapper.UserMapper;
import person.xianglin.simple.model.SysUser;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public SysUser getUserById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<SysUser> listUsers() {
        return userMapper.selectAll();
    }
}
