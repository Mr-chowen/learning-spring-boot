package com.xust.mapper;

import com.xust.model.OpLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface OpLogMapper {
    int deleteById(Integer id);

    int insert(OpLog opLog);

    int insertSelective(OpLog opLog);

    OpLog selectById(Integer id);

    int updateById(OpLog opLog);

    int updateByIdSelective(OpLog opLog);
}
