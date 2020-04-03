package com.xust.mall.service;

import com.xust.mall.common.CommonPage;
import com.xust.mall.common.utils.PageUtil;
import com.xust.mall.model.GoodsCategory;
import com.xust.mall.vo.IndexGoodsCategoryVO;
import com.xust.mall.vo.SearchPageCategoryVO;

import java.util.List;

public interface GoodsCategoryService {

    CommonPage listCategory(PageUtil pageUtil);

    String save(GoodsCategory goodsCategory);

    String update(GoodsCategory goodsCategory);

    GoodsCategory selectGoodsCategoryById(Long id);

    boolean deleteBatch(Long[] ids);

    List<GoodsCategory> selectByLevelAndParentIdsAndNumber(List<Long> parentIds, int categoryLevel);

    /**
     * 首页分类数据
     * @return
     */
    List<IndexGoodsCategoryVO> getCategoriesForIndex();

    /**
     * 搜索页分类数据
     * @param categoryId
     * @return
     */
    SearchPageCategoryVO getCategoriesForSearch(Long categoryId);

}
