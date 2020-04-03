package com.xust.mall.service.impl;


import com.xust.mall.common.CommonPage;
import com.xust.mall.common.enums.GoodsCategoryLevelEnum;
import com.xust.mall.common.enums.ServiceResultEnum;
import com.xust.mall.common.utils.BeanUtil;
import com.xust.mall.common.utils.ConstantsUtil;
import com.xust.mall.common.utils.PageUtil;
import com.xust.mall.mapper.GoodsCategoryMapper;
import com.xust.mall.model.GoodsCategory;
import com.xust.mall.service.GoodsCategoryService;
import com.xust.mall.vo.IndexGoodsCategoryVO;
import com.xust.mall.vo.SearchPageCategoryVO;
import com.xust.mall.vo.SecondLevelCategoryVO;
import com.xust.mall.vo.ThirdLevelCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.groupingBy;

@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {
    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    /**
     * 分页处理
     * @param pageUtil
     * @return
     */
    @Override
    public CommonPage listCategory(PageUtil pageUtil) {
        List<GoodsCategory> goodsCategories = goodsCategoryMapper.selectGoodsCategorys(pageUtil);
        int total = goodsCategoryMapper.getTotalGoodsCategories(pageUtil);
        CommonPage page = new CommonPage(goodsCategories,total,pageUtil.getLimit(),pageUtil.getPage());
        return page;
    }

    /**
     * 保存
     * @param goodsCategory
     * @return
     */
    @Override
    public String save(GoodsCategory goodsCategory) {
        GoodsCategory category = goodsCategoryMapper.selectByLevelAndName(goodsCategory.getCategoryLevel(),goodsCategory.getCategoryName());
        if(category != null){
            return ServiceResultEnum.SAME_CATEGORY_EXIST.getMessage();
        }
        if(goodsCategoryMapper.insertSelective(goodsCategory)>0){
            return ServiceResultEnum.SUCCESS.getMessage();
        }
        return ServiceResultEnum.DB_ERROR.getMessage();
    }

    /**
     * 修改
     * @param goodsCategory
     * @return
     */
    @Override
    public String update(GoodsCategory goodsCategory) {
        GoodsCategory category = goodsCategoryMapper.selectByPrimaryKey(goodsCategory.getCategoryId());
        if(category == null){
            return ServiceResultEnum.DATA_NOT_EXIST.getMessage();
        }
        GoodsCategory temp = goodsCategoryMapper.selectByLevelAndName(goodsCategory.getCategoryLevel(),goodsCategory.getCategoryName());
        //同名不同id,不可以修改
        if(temp != null && !temp.getCategoryId().equals(goodsCategory.getCategoryId())){
            return ServiceResultEnum.SAME_CATEGORY_EXIST.getMessage();
        }
        goodsCategory.setUpdateTime(new Date());
        if(goodsCategoryMapper.updateByPrimaryKeySelective(goodsCategory)>0){
            return ServiceResultEnum.SUCCESS.getMessage();
        }
        return ServiceResultEnum.DB_ERROR.getMessage();
    }

    /**
     * 根据i的查询商品类别
     * @param id
     * @return
     */
    @Override
    public GoodsCategory selectGoodsCategoryById(Long id) {
        return goodsCategoryMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Override
    public boolean deleteBatch(Long[] ids) {
        if(ids.length<1){
            return false;
        }
        return goodsCategoryMapper.deleteBatch(ids) > 0;
    }

    @Override
    public List<GoodsCategory> selectByLevelAndParentIdsAndNumber(List<Long> parentIds, int categoryLevel) {
        return goodsCategoryMapper.selectByLevelAndParentIdsAndNumber(parentIds,categoryLevel,0);
    }

    @Override
    public List<IndexGoodsCategoryVO> getCategoriesForIndex() {
        List<IndexGoodsCategoryVO> indexCategoryVOS = new ArrayList<>();
        //获取一级分类的固定数量的数据
        List<GoodsCategory> firstLevelCategories = goodsCategoryMapper.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), GoodsCategoryLevelEnum.LEVEL_ONE.getLevel(), ConstantsUtil.INDEX_CATEGORY_NUMBER);
        if (!CollectionUtils.isEmpty(firstLevelCategories)) {
            List<Long> firstLevelCategoryIds = firstLevelCategories.stream().map(GoodsCategory::getCategoryId).collect(Collectors.toList());
            //获取二级分类的数据
            List<GoodsCategory> secondLevelCategories = goodsCategoryMapper.selectByLevelAndParentIdsAndNumber(firstLevelCategoryIds, GoodsCategoryLevelEnum.LEVEL_TWO.getLevel(), 0);
            if (!CollectionUtils.isEmpty(secondLevelCategories)) {
                List<Long> secondLevelCategoryIds = secondLevelCategories.stream().map(GoodsCategory::getCategoryId).collect(Collectors.toList());
                //获取三级分类的数据
                List<GoodsCategory> thirdLevelCategories = goodsCategoryMapper.selectByLevelAndParentIdsAndNumber(secondLevelCategoryIds, GoodsCategoryLevelEnum.LEVEL_THREE.getLevel(), 0);
                if (!CollectionUtils.isEmpty(thirdLevelCategories)) {
                    //根据 parentId 将 thirdLevelCategories 分组
                    Map<Long, List<GoodsCategory>> thirdLevelCategoryMap = thirdLevelCategories.stream().collect(groupingBy(GoodsCategory::getParentId));
                    List<SecondLevelCategoryVO> secondLevelCategoryVOS = new ArrayList<>();
                    //处理二级分类
                    for (GoodsCategory secondLevelCategory : secondLevelCategories) {
                        SecondLevelCategoryVO secondLevelCategoryVO = new SecondLevelCategoryVO();
                        BeanUtil.copyProperties(secondLevelCategory, secondLevelCategoryVO);
                        //如果该二级分类下有数据则放入 secondLevelCategoryVOS 对象中
                        if (thirdLevelCategoryMap.containsKey(secondLevelCategory.getCategoryId())) {
                            //根据二级分类的id取出thirdLevelCategoryMap分组中的三级分类list
                            List<GoodsCategory> tempGoodsCategories = thirdLevelCategoryMap.get(secondLevelCategory.getCategoryId());
                            secondLevelCategoryVO.setThirdLevelCategoryVOS((BeanUtil.copyList(tempGoodsCategories, ThirdLevelCategoryVO.class)));
                            secondLevelCategoryVOS.add(secondLevelCategoryVO);
                        }
                    }
                    //处理一级分类
                    if (!CollectionUtils.isEmpty(secondLevelCategoryVOS)) {
                        //根据 parentId 将 thirdLevelCategories 分组
                        Map<Long, List<SecondLevelCategoryVO>> secondLevelCategoryVOMap = secondLevelCategoryVOS.stream().collect(groupingBy(SecondLevelCategoryVO::getParentId));
                        for (GoodsCategory firstCategory : firstLevelCategories) {
                            IndexGoodsCategoryVO indexCategoryVO = new IndexGoodsCategoryVO();
                            BeanUtil.copyProperties(firstCategory, indexCategoryVO);
                            //如果该一级分类下有数据则放入 newBeeMallIndexCategoryVOS 对象中
                            if (secondLevelCategoryVOMap.containsKey(firstCategory.getCategoryId())) {
                                //根据一级分类的id取出secondLevelCategoryVOMap分组中的二级级分类list
                                List<SecondLevelCategoryVO> tempGoodsCategories = secondLevelCategoryVOMap.get(firstCategory.getCategoryId());
                                indexCategoryVO.setSecondLevelCategoryVOS(tempGoodsCategories);
                                indexCategoryVOS.add(indexCategoryVO);
                            }
                        }
                    }
                }
            }
            return indexCategoryVOS;
        } else {
            return null;
        }
    }

    @Override
    public SearchPageCategoryVO getCategoriesForSearch(Long categoryId) {
        SearchPageCategoryVO searchPageCategoryVO = new SearchPageCategoryVO();
        GoodsCategory thirdLevelGoodsCategory = goodsCategoryMapper.selectByPrimaryKey(categoryId);
        if (thirdLevelGoodsCategory != null && thirdLevelGoodsCategory.getCategoryLevel() == GoodsCategoryLevelEnum.LEVEL_THREE.getLevel()) {
            //获取当前三级分类的二级分类
            GoodsCategory secondLevelGoodsCategory = goodsCategoryMapper.selectByPrimaryKey(thirdLevelGoodsCategory.getParentId());
            if (secondLevelGoodsCategory != null && secondLevelGoodsCategory.getCategoryLevel() == GoodsCategoryLevelEnum.LEVEL_TWO.getLevel()) {
                //获取当前二级分类下的三级分类List
                List<GoodsCategory> thirdLevelCategories = goodsCategoryMapper.selectByLevelAndParentIdsAndNumber(Collections.singletonList(secondLevelGoodsCategory.getCategoryId()), GoodsCategoryLevelEnum.LEVEL_THREE.getLevel(), ConstantsUtil.SEARCH_CATEGORY_NUMBER);
                searchPageCategoryVO.setCurrentCategoryName(thirdLevelGoodsCategory.getCategoryName());
                searchPageCategoryVO.setSecondLevelCategoryName(secondLevelGoodsCategory.getCategoryName());
                searchPageCategoryVO.setThirdLevelCategoryList(thirdLevelCategories);
                return searchPageCategoryVO;
            }
        }
        return null;
    }

}
