package com.xust.mall.mapper;

import com.xust.mall.common.utils.PageUtil;
import com.xust.mall.model.IndexConfig;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IndexConfigMapper {

    int deleteByPrimaryKey(Long configId);

    int deleteBatch(Long[] ids);

    int insert(IndexConfig indexConfig);

    int insertSelective(IndexConfig config);

    IndexConfig selectByPrimaryKey(Long configId);

    List<IndexConfig> selectIndexConfigs(PageUtil pageUtil);

    List<IndexConfig> selectIndexConfigByTypeAndNum(@Param("configType") int configType,@Param("number") int number);

    int getTotalIndexConfigs(PageUtil pageUtil);

    int updateByPrimaryKey(IndexConfig indexConfig);

    int updateByPrimaryKeySelective(IndexConfig indexConfig);
}
