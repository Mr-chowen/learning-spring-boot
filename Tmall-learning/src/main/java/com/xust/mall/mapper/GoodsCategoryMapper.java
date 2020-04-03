package com.xust.mall.mapper;

import com.xust.mall.common.utils.PageUtil;
import com.xust.mall.model.GoodsCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GoodsCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsCategory goodsCategory);

    int insertSelective(GoodsCategory goodsCategory);

    GoodsCategory selectByPrimaryKey(Long id);

    GoodsCategory selectByLevelAndName(@Param("categoryLevel") Byte categoryLevel,@Param("categoryName") String categoryName);

    List<GoodsCategory> selectGoodsCategorys(PageUtil pageUtil);

    List<GoodsCategory> selectByLevelAndParentIdsAndNumber(@Param("parentIds") List<Long> parentIds, @Param("categoryLevel") int categoryLevel, @Param("number") int number);

    int getTotalGoodsCategories(PageUtil pageUtil);

    int deleteBatch(Long[] ids);

    int updateByPrimaryKey(GoodsCategory goodsCategory);

    int updateByPrimaryKeySelective(GoodsCategory goodsCategory);
}
