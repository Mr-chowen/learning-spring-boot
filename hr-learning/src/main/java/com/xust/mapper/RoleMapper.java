package com.xust.mapper;

import com.xust.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface RoleMapper {
    int deleteById(Integer id);

    int insert(Role role);

    int insertSelective(Role role);

    Role selectById(Integer id);

    int updateById(Role role);

    int updateByIdSelective(Role role);

    List<Role> getAllRoles();
}
