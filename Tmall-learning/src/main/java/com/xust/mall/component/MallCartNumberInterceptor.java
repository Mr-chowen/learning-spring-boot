package com.xust.mall.component;

import com.xust.mall.common.utils.ConstantsUtil;
import com.xust.mall.mapper.ShoppingCartItemMapper;
import com.xust.mall.vo.MallUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class MallCartNumberInterceptor implements HandlerInterceptor {
    @Autowired
    private ShoppingCartItemMapper shoppingCartItemMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(null != request.getSession() && null != request.getSession().getAttribute(ConstantsUtil.MALL_USER_SESSION_KEY)){
            MallUserVO mallUserVO = (MallUserVO) request.getSession().getAttribute(ConstantsUtil.MALL_USER_SESSION_KEY);
            mallUserVO.setShopCartItemCount(shoppingCartItemMapper.selectCountByUserId(mallUserVO.getUserId()));
            request.getSession().setAttribute(ConstantsUtil.MALL_USER_SESSION_KEY,mallUserVO);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
