package com.xust.mall.controller.mall;

import com.xust.mall.common.CommonResult;
import com.xust.mall.common.ResultCode;
import com.xust.mall.common.enums.ServiceResultEnum;
import com.xust.mall.common.utils.ConstantsUtil;
import com.xust.mall.model.ShoppingCartItem;
import com.xust.mall.service.ShoppingCartService;
import com.xust.mall.vo.MallUserVO;
import com.xust.mall.vo.ShoppingCartItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 购物车
     * @param request
     * @param session
     * @return
     */
    @GetMapping("/shop-cart")
    public String cartListPage(HttpServletRequest request, HttpSession session){
        MallUserVO userVO = (MallUserVO) session.getAttribute(ConstantsUtil.MALL_USER_SESSION_KEY);
        int itemsTotal = 0;
        int priceTotal = 0;
        List<ShoppingCartItemVO> shoppingCartItemVOS = shoppingCartService.getMyShoppingCartItems(userVO.getUserId());
        if(!CollectionUtils.isEmpty(shoppingCartItemVOS)){
            //购物车总数
            itemsTotal = shoppingCartItemVOS.stream().mapToInt(ShoppingCartItemVO::getGoodsCount).sum();
            if(itemsTotal < 1){
                return "error/error_5xx";
            }
            //总价
            for (ShoppingCartItemVO shoppingCartItemVO : shoppingCartItemVOS){
                priceTotal += shoppingCartItemVO.getGoodsCount() * shoppingCartItemVO.getSellingPrice();
            }
            if(priceTotal < 1){
                return "error/error_5xx";
            }
        }
        request.setAttribute("itemsTotal",itemsTotal);
        request.setAttribute("priceTotal",priceTotal);
        request.setAttribute("shoppingCartItemVOS",shoppingCartItemVOS);
        return "mall/cart";
    }

    /**
     * 添加到购物车
     * @param shoppingCartItem
     * @param session
     * @return
     */
    @PostMapping("/shop-cart")
    @ResponseBody
    public CommonResult saveShoppingCartItem(@RequestBody ShoppingCartItem shoppingCartItem,HttpSession session){
        MallUserVO mallUserVO = (MallUserVO) session.getAttribute(ConstantsUtil.MALL_USER_SESSION_KEY);
        shoppingCartItem.setUserId(mallUserVO.getUserId());

        String result = shoppingCartService.saveCartItem(shoppingCartItem);
        if(ServiceResultEnum.SUCCESS.getMessage().equals(result)){
            return ResultCode.SuccessResult();
        }
        return ResultCode.FailResult(result);
    }

    /**
     * 修改购物车信息
     * @param shoppingCartItem
     * @param session
     * @return
     */
    @PutMapping("/shop-cart")
    @ResponseBody
    public CommonResult updateShoppingCartItem(@RequestBody ShoppingCartItem shoppingCartItem,HttpSession session){
        MallUserVO mallUserVO = (MallUserVO) session.getAttribute(ConstantsUtil.MALL_USER_SESSION_KEY);
        shoppingCartItem.setUserId(mallUserVO.getUserId());

        String result = shoppingCartService.updateCartItem(shoppingCartItem);
        if (ServiceResultEnum.SUCCESS.getMessage().equals(result)) {
            return ResultCode.SuccessResult();
        }
        return ResultCode.FailResult(result);
    }

    /**
     * 删除购物车中的商品
     * @param shoppingCartItemId
     * @param session
     * @return
     */
    @DeleteMapping("/shop-cart/{shoppingCartItemId}")
    @ResponseBody
    public CommonResult deleteShoppingCartItem(@PathVariable("shoppingCartItemId") Long shoppingCartItemId,HttpSession session){
        MallUserVO userVO = (MallUserVO) session.getAttribute(ConstantsUtil.MALL_USER_SESSION_KEY);
        boolean result = shoppingCartService.deleteById(shoppingCartItemId);

        if(result){
            return ResultCode.SuccessResult();
        }
        return ResultCode.FailResult(ServiceResultEnum.OPERATE_ERROR.getMessage());
    }

    /**
     * 结算
     * @param request
     * @param session
     * @return
     */
    @GetMapping("/shop-cart/settle")
    public String settlePage(HttpServletRequest request,HttpSession session){
        int priceTotal = 0;
        MallUserVO mallUserVO = (MallUserVO) session.getAttribute(ConstantsUtil.MALL_USER_SESSION_KEY);
        List<ShoppingCartItemVO> shoppingCartItemVOS = shoppingCartService.getMyShoppingCartItems(mallUserVO.getUserId());
        if(CollectionUtils.isEmpty(shoppingCartItemVOS)){
            return "mall/cart";
        } else {
            for(ShoppingCartItemVO shoppingCartItemVO : shoppingCartItemVOS){
                priceTotal += shoppingCartItemVO.getGoodsCount() * shoppingCartItemVO.getSellingPrice();
            }
            if(priceTotal < 1){
                return "error/error_5xx";
            }
        }
        request.setAttribute("priceTotal",priceTotal);
        request.setAttribute("shoppingCartItemVOS",shoppingCartItemVOS);
        return "mall/order-settle";

    }
}
