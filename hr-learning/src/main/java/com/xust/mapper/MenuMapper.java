package com.xust.mapper;

import com.xust.model.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MenuMapper {
    int deleteById(Integer id);

    int insert(Menu menu);

    int insertSelective(Menu menu);

    Menu selectById(Integer id);

    int updateById(Menu menu);

    int updateByIdSelective(Menu menu);

    List<Menu> getMenusByHrId(Integer hrid);

    List<Menu> getAllMenusWithRole();

    List<Menu> getAllMenus();

    List<Integer> getMidsByRid(Integer rid);

}
