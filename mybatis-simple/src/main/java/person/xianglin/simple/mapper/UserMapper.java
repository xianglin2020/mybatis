package person.xianglin.simple.mapper;

import org.apache.ibatis.annotations.Param;
import person.xianglin.simple.model.SysRole;
import person.xianglin.simple.model.SysUser;

import java.util.List;
import java.util.Map;

/**
 * @author xianglin
 */
public interface UserMapper {
    /**
     * 通过ID查找用户
     * 接口方法返回值是由 resultType或resultMap中的type决定的
     *
     * @param id 用户ID
     * @return 用户实例
     */
    SysUser selectById(Long id);

    /**
     * 查询全部用户
     *
     * @return 用户集合
     */
    List<SysUser> selectAll();

    /**
     * 通过用户ID查询所有角色
     *
     * @param id 用户ID
     * @return 角色集合
     */
    List<SysRole> selectRolesByUserId(Long id);

    /**
     * 新增用户
     *
     * @param sysUser 用户实例
     * @return 成功记录
     */
    int insert(SysUser sysUser);

    /**
     * 新增用户使用数据库的自增主键
     *
     * @param sysUser 用户实例
     * @return 成功记录
     */
    int insert2(SysUser sysUser);

    /**
     * 新增用户使用<selectKey>返回主键
     *
     * @param sysUser 用户实例
     * @return 成功记录
     */
    int insert3(SysUser sysUser);

    /**
     * 根据主键更新用户信息
     *
     * @param sysUser 更新后的对象实例
     * @return 受影响的行数
     */
    int updateById(SysUser sysUser);

    /**
     * 根据主键删除记录
     *
     * @param id 主键
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据主键删除记录
     *
     * @param user 实体
     * @return 受影响的行数
     */
    int deleteById(SysUser user);

    /**
     * 根据用户ID和角色状态查询角色信息
     * 未使用@Param注解指定参数时抛出异常
     * Parameter 'id' not found. Available parameters are [arg1, arg0, param1, param2]
     * 使用@Param注解后MyBatis会自动将参数封装为Map类型，参数值会作为Map的Key
     * param1, param2仍可正常使用
     * <p>
     * 如果使用Java8的带参数编译选项，即可正常使用
     * <compilerArgs>
     * <arg>-parameters</arg>
     * </compilerArgs>
     *
     * @param id      用户ID
     * @param enabled 角色状态
     * @return 角色集合
     */
    List<SysRole> selectRolesByUserIdAndRoleEnabled(@Param("id") Long id, @Param("enabled") Integer enabled);

    /**
     * 多个条件查询User
     * 使用mybatis动态SQL if标签
     *
     * @param user user
     * @return 列表
     */
    List<SysUser> selectByUser(SysUser user);

    /**
     * 使用trim实现where
     *
     * @param user user
     * @return list
     */
    List<SysUser> selectByUserWithTrim(SysUser user);

    /**
     * 有选择的更新字段
     *
     * @param user user
     * @return 受影响的行数
     */
    int updateByIdSelective(SysUser user);

    /**
     * 根据Id或userName查询用户信息
     *
     * @param user user
     * @return 用户
     */
    SysUser selectUserByIdOrUserName(SysUser user);

    /**
     * 使用foreach标签实现循环
     * 根据用户id查询集合
     *
     * @param idList id
     * @return 集合
     */
    List<SysUser> selectByIdList(List<Long> idList);

    /**
     * 使用foreach标签，参数是数组
     *
     * @param idArr id
     * @return 集合
     */
    List<SysUser> selectByIdArray(Long[] idArr);

    /**
     * 使用foreach标签，参数是map
     *
     * @param map map
     * @return 集合
     */
    List<SysUser> selectByIdMap(Map<String, Long> map);

    /**
     * 批量插入
     *
     * @param list list
     * @return 插入行数
     */
    int insertList(List<SysUser> list);

    /**
     * 使用foreach标签和map动态更新字段
     *
     * @param map map
     * @return 受影响的行数
     */
    int updateByMap(Map<String, Object> map);

    /**
     * 关联的嵌套结果查询
     * 查询用户及角色
     *
     * @param id 用户ID
     * @return 用户
     */
    SysUser selectUserAndRoleById(Long id);

    /**
     * 使用resultMap标签配置一对一映射
     * 查询用户及角色
     *
     * @param id 用户ID
     * @return 用户
     */
    SysUser selectUserAndRoleById2(@Param("id") Long id);

    /**
     * 使用 association 标签的嵌套查询
     *
     * @param id id
     * @return 结果
     */
    SysUser selectUserAndRoleByIdSelect(Long id);

    /**
     * 查询所有用户和角色
     *
     * @return 列表
     */
    List<SysUser> selectAllUserAndRoles();

    /**
     * 查询所有的用户角色权限
     *
     * @return list
     */
    List<SysUser> selectAllUserAndRoleAndPrivileges();

    /**
     * 查询所有的用户角色权限
     *
     * @param id 用户ID
     * @return 列表
     */
    SysUser selectAllUserAndRolesSelect(Long id);

    /**
     * 使用存储过程查询用户信息
     *
     * @param user user
     */
    void selectUserById(SysUser user);
}