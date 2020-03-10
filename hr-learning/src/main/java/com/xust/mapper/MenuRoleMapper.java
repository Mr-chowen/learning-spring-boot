package com.xust.mapper;

import com.xust.model.MenuRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface MenuRoleMapper {
    int deleteById(Integer id);

    int insert(MenuRole menuRole);

    int insertSelective(MenuRole menuRole);

    MenuRole selectById(Integer id);

    int updateById(MenuRole menuRole);

    int updateByIdSelective(MenuRole menuRole);

    void deleteByRid(Integer rid);

    Integer insertRecord(@Param("rid") Integer rid,@Param("mids")Integer[] mids);
}
