package com.xust.mall.mapper;

import com.xust.mall.model.ShoppingCartItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ShoppingCartItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShoppingCartItem shoppingCartItem);

    int insertSelective(ShoppingCartItem shoppingCartItem);

    ShoppingCartItem selectByPrimaryKey(Long id);

    ShoppingCartItem selectByUserIdAndGoodsId(@Param("mallUserId") Long mallUserId,@Param("goodsId") Long goodsId);

    List<ShoppingCartItem> selectByUserId(@Param("mallUserId") Long mallUserId,@Param("number") int number);

    int selectCountByUserId(Long mallUserId);

    int updateByPrimaryKey(ShoppingCartItem shoppingCartItem);

    int updateByPrimaryKeySelective(ShoppingCartItem shoppingCartItem);

    int deleteBatch(List<Long> ids);

}
