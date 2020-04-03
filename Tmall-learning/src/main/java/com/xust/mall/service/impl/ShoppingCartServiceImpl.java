package com.xust.mall.service.impl;

import com.xust.mall.common.enums.ServiceResultEnum;
import com.xust.mall.common.utils.BeanUtil;
import com.xust.mall.common.utils.ConstantsUtil;
import com.xust.mall.mapper.GoodsMapper;
import com.xust.mall.mapper.OrderItemMapper;
import com.xust.mall.mapper.ShoppingCartItemMapper;
import com.xust.mall.model.Goods;
import com.xust.mall.model.ShoppingCartItem;
import com.xust.mall.service.ShoppingCartService;
import com.xust.mall.vo.ShoppingCartItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartItemMapper shoppingCartItemMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public String saveCartItem(ShoppingCartItem shoppingCartItem) {
        ShoppingCartItem temp = shoppingCartItemMapper.selectByUserIdAndGoodsId(shoppingCartItem.getUserId(),shoppingCartItem.getGoodsId());
        if(temp != null){
            temp.setGoodsCount(shoppingCartItem.getGoodsCount());
            return updateCartItem(temp);
        }

        Goods goods = goodsMapper.selectByPrimaryKey(shoppingCartItem.getGoodsId());
        //商品为空
        if(goods == null){
            return ServiceResultEnum.GOODS_NOT_EXIST.getMessage();
        }
        int totalItem = shoppingCartItemMapper.selectCountByUserId(shoppingCartItem.getUserId());
        //购物车超出最大数量
        if(totalItem > ConstantsUtil.SHOPPING_CART_ITEM_LIMIT_NUMBER){
            return ServiceResultEnum.SHOPPING_CART_ITEM_LIMIT_NUMBER_ERROR.getMessage();
        }

        if(shoppingCartItemMapper.insertSelective(shoppingCartItem) > 0){
            return ServiceResultEnum.SUCCESS.getMessage();
        }
        return ServiceResultEnum.DB_ERROR.getMessage();
    }

    @Override
    public String updateCartItem(ShoppingCartItem shoppingCartItem) {
        ShoppingCartItem shoppingCartItemUpdate = shoppingCartItemMapper.selectByPrimaryKey(shoppingCartItem.getCartItemId());
        if(shoppingCartItemUpdate == null){
            return ServiceResultEnum.DATA_NOT_EXIST.getMessage();
        }
        if(shoppingCartItem.getGoodsCount() > ConstantsUtil.SHOPPING_CART_ITEM_LIMIT_NUMBER){
            return ServiceResultEnum.SHOPPING_CART_ITEM_LIMIT_NUMBER_ERROR.getMessage();
        }
        shoppingCartItemUpdate.setGoodsCount(shoppingCartItem.getGoodsCount());
        shoppingCartItem.setUpdateTime(new Date());

        if(shoppingCartItemMapper.updateByPrimaryKeySelective(shoppingCartItemUpdate) > 0){
            return ServiceResultEnum.SUCCESS.getMessage();
        }
        return ServiceResultEnum.DB_ERROR.getMessage();
    }

    @Override
    public ShoppingCartItem getCartItemById(Long shoppingCartItemId) {
        return shoppingCartItemMapper.selectByPrimaryKey(shoppingCartItemId);
    }

    @Override
    public Boolean deleteById(Long shoppingItemId) {
        return shoppingCartItemMapper.deleteByPrimaryKey(shoppingItemId) > 0;
    }

    @Override
    public List<ShoppingCartItemVO> getMyShoppingCartItems(Long mallUserId) {
        List<ShoppingCartItemVO> shoppingCartItemVOS = new ArrayList<>();
        List<ShoppingCartItem> shoppingCartItems = shoppingCartItemMapper.selectByUserId(mallUserId,ConstantsUtil.SHOPPING_CART_ITEM_TOTAL_NUMBER);

        if(!CollectionUtils.isEmpty(shoppingCartItems)){
            List<Long> goodsIds = shoppingCartItems.stream().map(ShoppingCartItem::getGoodsId).collect(Collectors.toList());
            List<Goods> goods = goodsMapper.selectByPrimaryKeys(goodsIds);
            Map<Long,Goods> goodsMap = new HashMap<>();
            if(!CollectionUtils.isEmpty(goods)){
                goodsMap = goods.stream().collect(Collectors.toMap(Goods::getGoodsId, Function.identity(),(entity1,entity2)->entity1));
            }

            for(ShoppingCartItem shoppingCartItem : shoppingCartItems){
                ShoppingCartItemVO shoppingCartItemVO = new ShoppingCartItemVO();
                BeanUtil.copyProperties(shoppingCartItem,shoppingCartItemVO);
                if (goodsMap.containsKey(shoppingCartItem.getGoodsId())) {
                    Goods goodsTemp = goodsMap.get(shoppingCartItem.getGoodsId());
                    shoppingCartItemVO.setGoodsCoverImg(goodsTemp.getGoodsCoverImg());
                    String goodsName = goodsTemp.getGoodsName();

                    if(goodsName.length() > 30){
                        goodsName = goodsName.substring(0,30)+"...";
                    }

                    shoppingCartItemVO.setGoodsName(goodsName);
                    shoppingCartItemVO.setSellingPrice(goodsTemp.getSellingPrice());
                    shoppingCartItemVOS.add(shoppingCartItemVO);
                }
            }
        }

        return shoppingCartItemVOS;
    }
}
