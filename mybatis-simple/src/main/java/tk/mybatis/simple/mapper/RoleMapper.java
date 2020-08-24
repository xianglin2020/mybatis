package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.cache.decorators.FifoCache;
import tk.mybatis.simple.model.SysRole;

import java.util.List;

/**
 * 使用注解的方式处理Role相关的查询
 *
 * @author xianglin
 */
// Caches collection already contains value for tk.mybatis.simple.mapper.RoleMapper
// @CacheNamespace(eviction = FifoCache.class, flushInterval = 60000, size = 512) 与XML中 cache-ref 配合使用
@CacheNamespaceRef(RoleMapper.class)
public interface RoleMapper {


    /**
     * @param id 角色ID
     * @return 角色实例
     */
    @Select({"select id,role_name roleName,enabled,",
            "create_by createBy,create_time createTime",
            "from sys_role",
            "where id = #{id}"
    })
    SysRole selectById(Long id);

    /**
     * 使用 mapUnderscoreToCamelCase 做自动列名转换
     *
     * @param id 角色ID
     * @return 角色实例
     */
    @Select("select * from sys_role where id = #{id}")
    SysRole selectById2(Long id);

    /**
     * 使用@Results注解实现属性映射
     *
     * @param id 角色ID
     * @return 角色实例
     */
    @Results(id = "roleResultMap", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "enabled", column = "enabled"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "createTime", column = "create_time")
    })
    @Select("select * from sys_role where id = #{id}")
    SysRole selectById3(Long id);

    /**
     * 查询所有角色信息
     * 从mybatis3.3.1开始注解定义的Results可以公用，给@Results注解增加id属性
     * 使用@ResultMap注解引用引用即可
     * 当配合着使用<ResultMap>元素时，@ResultMap注解引用的也可以是<ResultMap>元素的id值
     *
     * @return 角色信息集合
     */
    @ResultMap("roleResultMap")
    @Select("select * from sys_role")
    List<SysRole> selectAll();

    /**
     * 插入新角色
     * 不需要返回主键的情况
     *
     * @param sysRole role实例
     * @return 受影响的行数
     */
    @Insert("insert into sys_role(id,role_name,enabled,create_by,create_time)values(#{id},#{roleName},#{enabled},#{createBy},#{createTime,jdbcType=TIMESTAMP})")
    int insert(SysRole sysRole);

    /**
     * 插入新角色，返回自增主键
     *
     * @param sysRole role实例
     * @return 插入的行数
     */
    @Insert("insert into sys_role(role_name,enabled,create_by,create_time)values(#{roleName},#{enabled},#{createBy},#{createTime,jdbcType=TIMESTAMP})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insert2(SysRole sysRole);

    /**
     * 插入新角色，返回非自增主键
     *
     * @param sysRole role实例
     * @return 插入的行数
     */
    @SelectKey(statement = "select last_insert_id()", keyColumn = "id", keyProperty = "id", before = false, resultType = Long.class)
    @Insert("insert into sys_role(role_name,enabled,create_by,create_time)values(#{roleName},#{enabled},#{createBy},#{createTime,jdbcType=TIMESTAMP})")
    int insert3(SysRole sysRole);

    /**
     * 根据id修改role内容
     *
     * @param role role实例
     * @return 受影响的行书
     */
    @Update("update sys_role set role_name = #{roleName},enabled = #{enabled},create_by = #{createBy}, create_time = #{createTime,jdbcType=TIMESTAMP} where id = #{id}")
    int updateById(SysRole role);

    /**
     * 根据id删除role激励
     *
     * @param id id
     * @return 删除的行数
     */
    @Delete("delete from sys_role where id = #{id} ")
    int deleteById(Long id);

    /**
     * 根据id查询角色信息
     *
     * @param id id
     * @return 角色
     */
    SysRole selectRoleById(Long id);

    /**
     * 根据用户ID查询角色信息
     *
     * @param id id
     * @return 集合
     */
    List<SysRole> selectRoleByUserId(Long id);

    /**
     * discriminator标签使用
     *
     * @param id userId
     * @return role
     */
    List<SysRole> selectRoleByUserIdChoose(Long id);
}