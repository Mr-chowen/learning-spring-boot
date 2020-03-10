package com.xust.mapper;

import com.xust.model.Nation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface NationMapper {
    int deleteById(Integer id);

    int insert(Nation nation);

    int insertSelective(Nation nation);

    Nation selectById(Integer id);

    int updateById(Nation nation);

    int updateByIdSelective(Nation nation);

    List<Nation> getAllNations();
}
