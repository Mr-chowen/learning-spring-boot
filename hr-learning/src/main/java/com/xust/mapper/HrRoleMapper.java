package com.xust.mapper;

import com.xust.model.HrRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface HrRoleMapper {
    int deleteById(Integer id);

    int insert(HrRole hrRole);

    int insertSelective(HrRole hrRole);

    HrRole selectById(Integer id);

    int updateById(HrRole hrRole);

    int updateByIdSelective(HrRole hrRole);

    void deleteByHrid(Integer hrid);

    Integer addRole(@Param("hrid") Integer hrid,@Param("rids") Integer[] rids);

}
