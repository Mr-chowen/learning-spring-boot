package com.xust.mall.controller.admin;

import com.xust.mall.common.CommonResult;
import com.xust.mall.common.enums.GoodsCategoryLevelEnum;
import com.xust.mall.common.ResultCode;
import com.xust.mall.common.enums.ServiceResultEnum;
import com.xust.mall.common.utils.ConstantsUtil;
import com.xust.mall.common.utils.PageUtil;
import com.xust.mall.model.Goods;
import com.xust.mall.model.GoodsCategory;
import com.xust.mall.service.GoodsCategoryService;
import com.xust.mall.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class AdminGoodsController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @GetMapping("/goods")
    public String goodsPage(HttpServletRequest request){
        request.setAttribute("path","mall_goods");
        return "admin/mall_goods";
    }

    @GetMapping("/goods/edit")
    public String edit(HttpServletRequest request){
        request.setAttribute("path","edit");
       //查询一级分类
        List<GoodsCategory> firstLevelCategories = goodsCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), GoodsCategoryLevelEnum.LEVEL_ONE.getLevel());
        if(!CollectionUtils.isEmpty(firstLevelCategories)){
            List<GoodsCategory> secondLevelCategories = goodsCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(firstLevelCategories.get(0).getCategoryId()), GoodsCategoryLevelEnum.LEVEL_TWO.getLevel());
            if(!CollectionUtils.isEmpty(secondLevelCategories)){
                List<GoodsCategory> thirdLevelCategories = goodsCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(secondLevelCategories.get(0).getCategoryId()), GoodsCategoryLevelEnum.LEVEL_THREE.getLevel());
                request.setAttribute("firstLevelCategories",firstLevelCategories);
                request.setAttribute("secondLevelCategories",secondLevelCategories);
                request.setAttribute("thirdLevelCategories",thirdLevelCategories);
                return "admin/mall_goods_edit";
            }
        }
        return "error/error_5xx";
    }

    @GetMapping("/goods/edit/{goodsId}")
    public String edit(HttpServletRequest request,@PathVariable("goodsId") Long goodsId){
        request.setAttribute("path","edit");
        Goods good = goodsService.getGoodsById(goodsId);
        if(good == null){
            return "error/error_400";
        }
        //商品级别处理
        if(good.getGoodsCategoryId() > 0){
            if(good.getGoodsCategoryId() != null || good.getGoodsCategoryId() > 0 ){
                GoodsCategory goodsCategory = goodsCategoryService.selectGoodsCategoryById(good.getGoodsCategoryId());
                if (goodsCategory != null && goodsCategory.getCategoryLevel() == GoodsCategoryLevelEnum.LEVEL_THREE.getLevel()){
                    List<GoodsCategory> firstLevelCategories = goodsCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(goodsCategory.getParentId()),GoodsCategoryLevelEnum.LEVEL_TWO.getLevel());
                    List<GoodsCategory> thirdLevelCategories = goodsCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(goodsCategory.getParentId()),GoodsCategoryLevelEnum.LEVEL_THREE.getLevel());
                    GoodsCategory secondCategory = goodsCategoryService.selectGoodsCategoryById(goodsCategory.getCategoryId());
                    if(secondCategory != null){
                        List<GoodsCategory> secondLevelCategories = goodsCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(secondCategory.getParentId()),GoodsCategoryLevelEnum.LEVEL_TWO.getLevel());
                        GoodsCategory firstCategory = goodsCategoryService.selectGoodsCategoryById(secondCategory.getParentId());
                        if(firstCategory != null){
                            request.setAttribute("firstLevelCategories",firstLevelCategories);
                            request.setAttribute("thirdLevelCategories",secondLevelCategories);
                            request.setAttribute("thirdLevelCategories",thirdLevelCategories);
                            request.setAttribute("firstLevelCategoryId",firstCategory.getCategoryId());
                            request.setAttribute("secondLevelCategoryId",secondCategory.getCategoryId());
                            request.setAttribute("thirdLevelCategoryId",goodsCategory.getCategoryId());
                        }
                    }
                }
            }
        }

        if(good.getGoodsCategoryId() == 0){
            List<GoodsCategory> firstLevelCategories = goodsCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L),GoodsCategoryLevelEnum.LEVEL_ONE.getLevel());
            if(!CollectionUtils.isEmpty(firstLevelCategories)){
                List<GoodsCategory> secondLevelCategories = goodsCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(firstLevelCategories.get(0).getCategoryId()),GoodsCategoryLevelEnum.LEVEL_TWO.getLevel());
                if(!CollectionUtils.isEmpty(secondLevelCategories)){
                    List<GoodsCategory> thirdLevelCategories = goodsCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(secondLevelCategories.get(0).getCategoryId()),GoodsCategoryLevelEnum.LEVEL_THREE.getLevel());
                    request.setAttribute("firstLevelCategories",firstLevelCategories);
                    request.setAttribute("secondLevelCategories",secondLevelCategories);
                    request.setAttribute("thirdLevelCategories",thirdLevelCategories);
                }
            }
        }
        request.setAttribute("goods",good);
        request.setAttribute("path","goods-edit");
        return "admin/mall_goods_edit";
    }

    /**
     * 查询分页显示
     * @param params
     * @return
     */
    @GetMapping("/goods/list")
    @ResponseBody
    public CommonResult list(@RequestParam Map<String,Object> params){
        if(StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))){
            return ResultCode.FailResult("请求参数异常");
        }
        PageUtil pageUtil = new PageUtil(params);
        return ResultCode.SuccessResult(goodsService.getGoodsPage(pageUtil));
    }

    /**
     * 添加
     * @param goods
     * @return
     */
    @PostMapping("/goods/save")
    @ResponseBody
    public CommonResult add(@RequestBody Goods goods){
        String result = goodsService.saveGoods(goods);
        if(ServiceResultEnum.SUCCESS.getMessage().equals(result)){
            return ResultCode.SuccessResult();
        } else {
            return ResultCode.FailResult(result);
        }
    }

    /**
     * 修改
     * @param goods
     * @return
     */
    @PostMapping("/goods/update")
    @ResponseBody
    public CommonResult update(@RequestBody Goods goods){
        if(StringUtils.isEmpty(goods.getGoodsName())
                || StringUtils.isEmpty(goods.getGoodsIntro())
                || StringUtils.isEmpty(goods.getTag())
                || Objects.isNull(goods.getOriginalPrice())
                || Objects.isNull(goods.getGoodsCategoryId())
                || Objects.isNull(goods.getSellingPrice())
                || Objects.isNull(goods.getStockNum())
                || Objects.isNull(goods.getGoodsSellStatus())
                || StringUtils.isEmpty(goods.getGoodsCoverImg())
                || StringUtils.isEmpty(goods.getGoodsDetailContent())){
            return ResultCode.FailResult("请检查输入信息");
        }
        String result = goodsService.saveGoods(goods);
        if(ServiceResultEnum.SUCCESS.getMessage().equals(result)){
            return ResultCode.SuccessResult();
        } else {
            return ResultCode.FailResult(result);
        }

    }

    /**
     * 详情信息
     * @param id
     * @return
     */
    @GetMapping("/goods/info/{id}")
    @ResponseBody
    public CommonResult info(@PathVariable("id") Long id){
        Goods goods = goodsService.getGoodsById(id);
        if(goods == null){
            return ResultCode.FailResult(ServiceResultEnum.GOODS_NOT_EXIST.getMessage());
        }
        return ResultCode.SuccessResult(goods);
    }

    /**
     * 批量修改商品状态
     * @param ids
     * @param sellStatus
     * @return
     */
    @PutMapping("goods/status/{sellStatus}")
    @ResponseBody
    public CommonResult delete(@RequestBody Long[] ids,@PathVariable("sellStatus") int sellStatus){
        if(ids.length < 1){
            return ResultCode.FailResult("请求参数异常...");
        }
        if(sellStatus != ConstantsUtil.SELL_STATUS_UP && sellStatus != ConstantsUtil.SELL_STATUS_DOWN){
            return ResultCode.FailResult("状态异常...");
        }
        if(goodsService.batchUpdateSellStatus(ids,sellStatus)){
            return ResultCode.SuccessResult();
        } else {
            return ResultCode.FailResult("更新状态异常,请检查...");
        }
    }
}
