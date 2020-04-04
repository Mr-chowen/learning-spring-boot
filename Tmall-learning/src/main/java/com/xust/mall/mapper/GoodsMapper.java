package com.xust.mall.mapper;

import com.xust.mall.common.utils.PageUtil;
import com.xust.mall.model.Goods;
import com.xust.mall.model.StockNumDTO;
import org.springframework.stereotype.Component;

import org.apache.ibatis.annotations.Param;

import java.util.List;

@Component
public interface GoodsMapper {
    int deleteByPrimaryKey(Long goodsId);

    int insert(Goods goods);

    int insertSelective(Goods goods);

    Goods selectByPrimaryKey(Long goodsId);

    int updateByPrimaryKeySelective(Goods goods);

    int updateByPrimaryKeyWithBLOBs(Goods goods);

    int updateByPrimaryKey(Goods record);

    List<Goods> selectGoods(PageUtil pageUtil);

    List<Goods> selectByPrimaryKeys(List<Long> goodsIds);

    List<Goods> selectGoodsBySearch(PageUtil pageUtil);

    int getTotalGoods(PageUtil pageUtil);

    int getTotalGoodsBySearch(PageUtil pageUtil);

    int batchInsert(@Param("goodsList") List<Goods> goodsList);

    int updateStockNum(@Param("stockNumDTOS") List<StockNumDTO> stockNumDTOS);

    int batchUpdateSellStatus(@Param("orderIds")Long[] orderIds,@Param("sellStatus") int sellStatus);
}
