package person.xianglin.simple.mapper;

import org.apache.ibatis.annotations.SelectProvider;
import person.xianglin.simple.model.SysPrivilege;
import person.xianglin.simple.util.PrivilegeProvider;

import java.util.List;


/**
 * @author xianglin
 */
public interface PrivilegeMapper {
    /**
     * 查询权限
     * type：指定一个包含method指定方法的类，此类必需包含无参构造方法
     * method：返回值即为要执行的SQL，方法返回值为String
     *
     * @param id id
     * @return privilege实例
     */
    @SelectProvider(type = PrivilegeProvider.class, method = "selectById")
    SysPrivilege selectById(Long id);

    /**
     * 根据角色ID查对应权限集合
     *
     * @param roleId 角色ID
     * @return 权限集
     */
    List<SysPrivilege> selectPrivilegeByRoleId(Long roleId);
}