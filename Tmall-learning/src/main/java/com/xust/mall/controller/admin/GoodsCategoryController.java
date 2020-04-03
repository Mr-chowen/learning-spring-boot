package com.xust.mall.controller.admin;

import com.xust.mall.common.*;
import com.xust.mall.common.enums.GoodsCategoryLevelEnum;
import com.xust.mall.common.enums.ServiceResultEnum;
import com.xust.mall.common.utils.PageUtil;
import com.xust.mall.model.GoodsCategory;
import com.xust.mall.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class GoodsCategoryController {

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @GetMapping("/categories")
    public String categoriesPage(HttpServletRequest request,@RequestParam("categoryLevel") Byte categoryLevel,@RequestParam("parentId") Long parentId,@RequestParam("backParentId") Long backParentId){
        if(categoryLevel == null || categoryLevel < 1 || categoryLevel > 3){
            return "error/error_5xx";
        }

        request.setAttribute("path","mall_category");
        request.setAttribute("parentId",parentId);
        request.setAttribute("backParentId",backParentId);
        request.setAttribute("categoryLevel",categoryLevel);
        return "admin/mall_category";
    }

    /**
     * 查询
     * @param params
     * @return
     */
    @GetMapping("/categories/list")
    @ResponseBody
    public CommonResult list(@RequestParam Map<String,Object> params){
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultCode.FailResult("请求参数异常！");
        }
        PageUtil pageUtil = new PageUtil(params);
        return ResultCode.SuccessResult(goodsCategoryService.listCategory(pageUtil));
    }

    /**
     * 根据id查询
     * @param categoryId
     * @return
     */
    @GetMapping("/categories/listForSelect")
    @ResponseBody
    public CommonResult listForSelect(@RequestParam("categoryId") Long categoryId){
        if(categoryId == null || categoryId < 1){
            return ResultCode.FailResult("请检查参数");
        }

        GoodsCategory category = goodsCategoryService.selectGoodsCategoryById(categoryId);

        if(category == null || category.getCategoryLevel() == GoodsCategoryLevelEnum.LEVEL_THREE.getLevel()){
            return ResultCode.FailResult("请检查参数");
        }

        Map categoryResult = new HashMap(2);
        if(category.getCategoryLevel() == GoodsCategoryLevelEnum.LEVEL_ONE.getLevel()){
            List<GoodsCategory> secondLevel = goodsCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(categoryId), GoodsCategoryLevelEnum.LEVEL_TWO.getLevel());
            if (!CollectionUtils.isEmpty(secondLevel)) {
                //查询二级分类列表中第一个实体的所有三级分类
                List<GoodsCategory> thirdLevelCategories = goodsCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(secondLevel.get(0).getCategoryId()), GoodsCategoryLevelEnum.LEVEL_THREE.getLevel());
                categoryResult.put("secondLevelCategories", secondLevel);
                categoryResult.put("thirdLevelCategories", thirdLevelCategories);
            }
        }

        if (category.getCategoryLevel() == GoodsCategoryLevelEnum.LEVEL_TWO.getLevel()) {
            //如果是二级分类则返回当前分类下的所有三级分类列表
            List<GoodsCategory> thirdLevelCategories = goodsCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(categoryId), GoodsCategoryLevelEnum.LEVEL_THREE.getLevel());
            categoryResult.put("thirdLevelCategories", thirdLevelCategories);
        }
        return ResultCode.SuccessResult(categoryResult);
    }

    /**
     * 添加
     * @param goodsCategory
     * @return
     */
    @PostMapping("/categories/save")
    @ResponseBody
    public CommonResult add(@RequestBody GoodsCategory goodsCategory){
        String result = goodsCategoryService.save(goodsCategory);
        if(ServiceResultEnum.SUCCESS.getMessage().equals(result)){
            return ResultCode.SuccessResult();
        } else {
            return ResultCode.FailResult(result);
        }
    }

    /**
     * 修改
     * @param goodsCategory
     * @return
     */
    @PostMapping("/categories/update")
    @ResponseBody
    public CommonResult update(@RequestBody GoodsCategory goodsCategory) {
        if (Objects.isNull(goodsCategory.getCategoryId())
                || Objects.isNull(goodsCategory.getCategoryLevel())
                || StringUtils.isEmpty(goodsCategory.getCategoryName())
                || Objects.isNull(goodsCategory.getParentId())
                || Objects.isNull(goodsCategory.getCategoryRank())) {

            return ResultCode.FailResult("请检查参数");
        }

        String result = goodsCategoryService.update(goodsCategory);
        if (ServiceResultEnum.SUCCESS.getMessage().equals(result)) {
            return ResultCode.SuccessResult();
        } else {
            return ResultCode.FailResult(result);
        }
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @GetMapping("/categories/info/{id}")
    @ResponseBody
    public CommonResult info(@PathVariable("id") Long id){
        GoodsCategory goodsCategory = goodsCategoryService.selectGoodsCategoryById(id);
        if(goodsCategory == null){
            return ResultCode.FailResult("未查询到数据");
        }
        return ResultCode.SuccessResult(goodsCategory);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/categories/delete")
    @ResponseBody
    public CommonResult delete(@RequestBody Long[] ids){
        if(ids.length < 1){
            return ResultCode.FailResult("请检查参数ids");
        }
        if(goodsCategoryService.deleteBatch(ids)){
            return ResultCode.SuccessResult();
        }else{
            return ResultCode.FailResult("删除失败");
        }
    }
}
