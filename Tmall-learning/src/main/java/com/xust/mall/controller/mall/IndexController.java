package com.xust.mall.controller.mall;

import com.xust.mall.common.enums.IndexConfigTypeEnum;
import com.xust.mall.common.utils.ConstantsUtil;
import com.xust.mall.service.CarouselService;
import com.xust.mall.service.GoodsCategoryService;
import com.xust.mall.service.IndexConfigService;
import com.xust.mall.vo.IndexCarouselVO;
import com.xust.mall.vo.IndexGoodsCategoryVO;
import com.xust.mall.vo.IndexGoodsConfigVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private CarouselService carouselService;

    @Autowired
    private IndexConfigService indexConfigService;

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @GetMapping({"/index","/","/index.html"})
    public String indexPage(HttpServletRequest request){
        List<IndexGoodsCategoryVO> categoryVOS = goodsCategoryService.getCategoriesForIndex();
        if(CollectionUtils.isEmpty(categoryVOS)){
            return "error/error_5xx";
        }
        List<IndexCarouselVO> carouselVOS = carouselService.getCarouselForIndex(ConstantsUtil.INDEX_CAROUSEL_NUMBER);
        List<IndexGoodsConfigVO> hotGoodses = indexConfigService.getConfigGoodsForIndex(IndexConfigTypeEnum.INDEX_GOODS_HOT.getType(),ConstantsUtil.INDEX_GOODS_HOT_NUMBER);
        List<IndexGoodsConfigVO> newGoodses = indexConfigService.getConfigGoodsForIndex(IndexConfigTypeEnum.INDEX_GOODS_NEW.getType(),ConstantsUtil.INDEX_GOODS_NEW_NUMBER);
        List<IndexGoodsConfigVO> recommendGoodses = indexConfigService.getConfigGoodsForIndex(IndexConfigTypeEnum.INDEX_GOODS_RECOMMOND.getType(),ConstantsUtil.INDEX_GOODS_RECOMMOND_NUMBER);
        request.setAttribute("categories", categoryVOS);//分类数据
        request.setAttribute("carousels", carouselVOS);//轮播图
        request.setAttribute("hotGoodses", hotGoodses);//热销商品
        request.setAttribute("newGoodses", newGoodses);//新品
        request.setAttribute("recommendGoodses", recommendGoodses);//推荐商品
        return "mall/index";
    }
}
