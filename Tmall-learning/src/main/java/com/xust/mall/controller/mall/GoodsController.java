package com.xust.mall.controller.mall;

import com.xust.mall.common.utils.BeanUtil;
import com.xust.mall.common.utils.ConstantsUtil;
import com.xust.mall.common.utils.PageUtil;
import com.xust.mall.model.Goods;
import com.xust.mall.service.GoodsCategoryService;
import com.xust.mall.service.GoodsService;
import com.xust.mall.vo.GoodsDetailVO;
import com.xust.mall.vo.SearchPageCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsCategoryService goodsCategoryService;

    /**
     * 商品搜索
     * @param params
     * @param request
     * @return
     */
    @GetMapping({"/search","/search.html"})
    public String searchPage(@RequestParam Map<String,Object> params, HttpServletRequest request){
        if(StringUtils.isEmpty(params.get("page"))){
            params.put("page",1);
        }
        params.put("limit", ConstantsUtil.GOODS_SEARCH_PAGE_LIMIT);
        //封装分类数据
        if(params.containsKey("goodsCategoryId") && !StringUtils.isEmpty(params.get("goodsCategoryId")+"")){
            Long categoryId = Long.valueOf(params.get("goodsCategoryId")+"");
            SearchPageCategoryVO searchPageCategoryVO = goodsCategoryService.getCategoriesForSearch(categoryId);
            if(searchPageCategoryVO != null){
                request.setAttribute("goodsCaegoryId",categoryId);
                request.setAttribute("searchPageCategoryVO",searchPageCategoryVO);
            }
        }

        //封装参数（回显）
        if(params.containsKey("orderBy") && !StringUtils.isEmpty(params.get("orderBy")+"")){
            request.setAttribute("orderBY",params.get("orderBy")+"");
        }

        //去空格
        String keyword = "";
        if(params.containsKey("keyword") && !StringUtils.isEmpty((params.get("keyword")+"").trim())){
            keyword = params.get("keyword")+"";
        }
        request.setAttribute("keyword",keyword);
        params.put("keyword",keyword);

        //封装商品数据
        PageUtil pageUtil = new PageUtil(params);
        request.setAttribute("pageResult",goodsService.searchGoods(pageUtil));
        return "mall/search";
    }

    /**
     * 商品详情
     * @param goodsId
     * @param request
     * @return
     */
    @GetMapping("/goods/detail/{goodsId}")
    public String detailPage(@PathVariable("goodsId") Long goodsId,HttpServletRequest request){
        if(goodsId < 1){
            return "error/error_5xx";
        }

        Goods goods = goodsService.getGoodsById(goodsId);
        if(goods == null){
            return "error/error_404";
        }

        GoodsDetailVO goodsDetailVO = new GoodsDetailVO();
        BeanUtil.copyProperties(goods,goodsDetailVO);
        goodsDetailVO.setGoodsCarouselList(goods.getGoodsCarousel().split(","));
        request.setAttribute("goodsDetail",goodsDetailVO);
        return "mall/detail";
    }
}
