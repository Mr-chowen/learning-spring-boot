package com.xust.mapper;

import com.xust.model.SysMsg;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SysMsgMapper {
    int deleteById(Integer id);

    int insert(SysMsg sysMsg);

    int insertSelective(SysMsg sysMsg);

    SysMsg selectById(Integer id);

    int updateById(SysMsg sysMsg);

    int updateByIdSelective(SysMsg sysMsg);
}
