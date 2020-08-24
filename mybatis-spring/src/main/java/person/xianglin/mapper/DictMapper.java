package person.xianglin.mapper;

import org.apache.ibatis.session.RowBounds;
import person.xianglin.model.SysDict;

import java.util.List;

/**
 * @author xianglin
 */
public interface DictMapper {

    /**
     * 通过主键查询
     *
     * @param id 主键
     * @return SysDict
     */
    SysDict selectByPrimaryKey(Long id);


    /**
     * 条件分页查询
     *
     * @param sysDict   查询条件
     * @param rowBounds 分页参数
     * @return 结果
     */
    List<SysDict> selectBySysDict(SysDict sysDict, RowBounds rowBounds);

    /**
     * 新增
     *
     * @param sysDict SysDict
     * @return 结果
     */
    int insert(SysDict sysDict);

    /**
     * 根据主键更新
     *
     * @param sysDict SysDict
     * @return 结果
     */
    int updateById(SysDict sysDict);

    /**
     * 根据主键删除
     *
     * @param id 主键
     * @return 结果
     */
    int deleteById(Long id);
}
