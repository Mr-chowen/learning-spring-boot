package com.xust.mapper;

import com.xust.model.Hr;
import com.xust.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface HrMapper {
    int deleteById(Integer id);

    int insert(Hr hr);

    int insertSelective(Hr hr);

    Hr selectById(Integer id);

    int updateById(Hr hr);

    int updateByIdSelective(Hr hr);

    Hr loadUserByUsername(String username);

    List<Role> getHrRoleById(Integer id);

    List<Hr> getAllHrs(@Param("hrid") Integer hrid,@Param("keywords") String keywords);

    List<Hr> getAllHrsExceptCurrentHr(Integer id);

    Integer updateUserface(@Param("uri") String uri,@Param("id") Integer id);

    Integer updatePassword(@Param("hrid") Integer hrid,@Param("encoderPassword") String encoderPassword);
}
