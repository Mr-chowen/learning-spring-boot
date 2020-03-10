package com.xust.mapper;

import com.xust.model.MsgContent;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface MsgContentMapper {
    int deleteById(Integer id);

    int insert(MsgContent msgContent);

    int insertSelective(MsgContent msgContent);

    MsgContent selectById(Integer id);

    int updateById(MsgContent msgContent);

    int updateByIdSelective(MsgContent msgContent);
}
