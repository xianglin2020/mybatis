package person.xianglin.service.impl;

import com.sun.istack.internal.NotNull;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import person.xianglin.mapper.DictMapper;
import person.xianglin.model.SysDict;
import person.xianglin.service.DictService;

import java.util.List;
import java.util.Objects;

@Service
public class DictServiceImpl implements DictService {
    private final DictMapper dictMapper;

    public DictServiceImpl(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }

    @Override
    public SysDict findById(@NotNull Long id) {
        return dictMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysDict> findBySysDict(SysDict sysDict, Integer offset, Integer limit) {
        RowBounds bounds = RowBounds.DEFAULT;
        if (Objects.nonNull(offset) && Objects.nonNull(limit)) {
            bounds = new RowBounds(offset, limit);
        }
        return dictMapper.selectBySysDict(sysDict, bounds);
    }

    @Override
    public boolean saveOrUpdate(SysDict sysDict) {
        return (Objects.isNull(sysDict.getId()) ? dictMapper.insert(sysDict) : dictMapper.updateById(sysDict)) == 1;
    }

    @Override
    public boolean deleteById(@NotNull Long id) {
        return dictMapper.deleteById(id) == 1;
    }
}
