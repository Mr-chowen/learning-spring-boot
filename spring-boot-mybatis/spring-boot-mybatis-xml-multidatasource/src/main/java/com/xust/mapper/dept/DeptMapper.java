package com.xust.mapper.dept;

import com.xust.entity.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface DeptMapper {
    List<Dept> findAll();

    Dept findById(long id);

    void insert(Dept dept);

    void update(Dept dept);

    void delete(long id);
}
