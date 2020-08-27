package person.xianglin.mybatisspringboot.service;

import person.xianglin.simple.model.SysUser;

import java.util.List;

/**
 * @author xianglin
 */
public interface IUserService {

    /**
     * 通过ID查询用户
     *
     * @param id ID
     * @return 用户信息
     */
    SysUser getUserById(Long id);

    /**
     * 查询全部用户信息
     *
     * @return 用户信息集合
     */
    List<SysUser> listUsers();
}
