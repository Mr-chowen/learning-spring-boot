package com.xust.mapper;

import com.xust.model.JobLevel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface JobLevelMapper {
    int deleteById(Integer id);

    int insert(JobLevel jobLevel);

    int insertSelective(JobLevel jobLevel);

    JobLevel selectById(Integer id);

    int updateById(JobLevel jobLevel);

    int updateByIdSelective(JobLevel jobLevel);

    List<JobLevel> getAllJobLevels();

    Integer deleteJobLevelsByIds(@Param("ids") Integer[] ids);
}
