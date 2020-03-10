package com.xust.mapper;

import com.xust.model.Politicsstatus;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PoliticsstatusMapper {
    int deleteById(Integer id);

    int insert(Politicsstatus politicsstatus);

    int insertSelective(Politicsstatus politicsstatus);

    Politicsstatus selectById(Integer id);

    int updateById(Politicsstatus politicsstatus);

    int updateByIdSelective(Politicsstatus politicsstatus);

    List<Politicsstatus> getAllPoliticsstatus();
}
