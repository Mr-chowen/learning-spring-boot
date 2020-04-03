package com.xust.mall.service;

import com.xust.mall.common.CommonPage;
import com.xust.mall.common.CommonResult;
import com.xust.mall.common.utils.PageUtil;
import com.xust.mall.model.Goods;

import java.util.List;

public interface GoodsService {
    /**
     * 后台分页
     *
     * @param pageUtil
     * @return
     */
    CommonPage getGoodsPage(PageUtil pageUtil);

    /**
     * 添加商品
     *
     * @param goods
     * @return
     */
    String saveGoods(Goods goods);

    /**
     * 批量新增商品数据
     *
     * @param goodsList
     * @return
     */
    void batchSaveGoods(List<Goods> goodsList);

    /**
     * 修改商品信息
     *
     * @param goods
     * @return
     */
    String updateGoods(Goods goods);

    /**
     * 获取商品详情
     *
     * @param id
     * @return
     */
    Goods getGoodsById(Long id);

    /**
     * 批量修改销售状态(上架下架)
     *
     * @param ids
     * @return
     */
    Boolean batchUpdateSellStatus(Long[] ids,int sellStatus);

    /**
     * 商品搜索
     *
     * @param pageUtil
     * @return
     */
    CommonPage searchGoods(PageUtil pageUtil);
}
