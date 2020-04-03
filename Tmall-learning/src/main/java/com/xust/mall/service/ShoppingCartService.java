package com.xust.mall.service;

import com.xust.mall.model.ShoppingCartItem;
import com.xust.mall.vo.ShoppingCartItemVO;

import java.util.List;

public interface ShoppingCartService {

    String saveCartItem(ShoppingCartItem shoppingCartItem);

    String updateCartItem(ShoppingCartItem shoppingCartItem);

    ShoppingCartItem getCartItemById(Long shoppingCartItemId);

    Boolean deleteById(Long shoppingItemId);

    List<ShoppingCartItemVO> getMyShoppingCartItems(Long mallUserId);
}
