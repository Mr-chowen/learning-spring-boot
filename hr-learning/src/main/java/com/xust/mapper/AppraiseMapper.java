package com.xust.mapper;

import com.xust.model.Appraise;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface AppraiseMapper {
    int deleteById(Integer id);

    int insert(Appraise appraise);

    int insertSelective(Appraise appraise);

    Appraise selectById(Integer id);

    int updateById(Appraise appraise);

    int updateByIdSelective(Appraise appraise);


}
