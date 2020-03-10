package com.xust.mapper;

import com.xust.model.Position;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PositionMapper {
    int deleteById(Integer id);

    int insert(Position position);

    int insertSelective(Position position);

    Position selectById(Integer id);

    int updateById(Position position);

    int updateByIdSelective(Position position);

    List<Position> getAllPositons();

    Integer deletePositionByIds(@Param("ids") Integer[] ids);
}
