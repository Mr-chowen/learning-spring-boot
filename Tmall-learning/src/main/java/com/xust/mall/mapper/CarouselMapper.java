package com.xust.mall.mapper;

import com.xust.mall.common.utils.PageUtil;
import com.xust.mall.model.Carousel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface CarouselMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Carousel carousel);

    int insertSelective(Carousel carousel);

    Carousel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Carousel carousel);

    int updateByPrimaryKey(Carousel carousel);

    List<Carousel> selectCarousels(PageUtil pageUtil);

    int getTotalCarousels(PageUtil pageUtil);

    int deleteBatch(Long ids[]);

    List<Carousel> selectByCarouselsNum(@Param("number") int number);
}
