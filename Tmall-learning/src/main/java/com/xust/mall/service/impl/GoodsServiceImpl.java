package com.xust.mall.service.impl;

import com.xust.mall.common.CommonPage;
import com.xust.mall.common.enums.ServiceResultEnum;
import com.xust.mall.common.utils.BeanUtil;
import com.xust.mall.common.utils.PageUtil;
import com.xust.mall.mapper.GoodsMapper;
import com.xust.mall.model.Goods;
import com.xust.mall.service.GoodsService;
import com.xust.mall.vo.SearchGoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public CommonPage getGoodsPage(PageUtil pageUtil) {
        List<Goods> list = goodsMapper.selectGoods(pageUtil);
        int total = goodsMapper.getTotalGoods(pageUtil);
        CommonPage page = new CommonPage(list,total,pageUtil.getLimit(),pageUtil.getPage());
        return page;
    }

    @Override
    public String saveGoods(Goods goods) {
        if(goodsMapper.insertSelective(goods) > 0){
            return ServiceResultEnum.SUCCESS.getMessage();
        }
        return ServiceResultEnum.DB_ERROR.getMessage();
    }

    @Override
    public void batchSaveGoods(List<Goods> goodsList) {
        if(!CollectionUtils.isEmpty(goodsList)){
            goodsMapper.batchInsert(goodsList);
        }
    }

    @Override
    public String updateGoods(Goods goods) {
        Goods temp = goodsMapper.selectByPrimaryKey(goods.getGoodsId());
        if(temp == null){
            return ServiceResultEnum.DATA_NOT_EXIST.getMessage();
        }
        goods.setUpdateTime(new Date());
        if(goodsMapper.updateByPrimaryKeySelective(goods) > 0){
            return ServiceResultEnum.SUCCESS.getMessage();
        }
        return ServiceResultEnum.DB_ERROR.getMessage();
    }

    @Override
    public Goods getGoodsById(Long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean batchUpdateSellStatus(Long[] ids, int sellStatus) {
        return goodsMapper.batchUpdateSellStatus(ids,sellStatus) > 0;
    }

    @Override
    public CommonPage searchGoods(PageUtil pageUtil) {
        List<Goods> goods = goodsMapper.selectGoodsBySearch(pageUtil);
        int total = goodsMapper.getTotalGoodsBySearch(pageUtil);
        List<SearchGoodsVO> goodsVOS = new ArrayList<>();
        if(!CollectionUtils.isEmpty(goods)){
            goodsVOS = BeanUtil.copyList(goods,SearchGoodsVO.class);
            for (SearchGoodsVO searchGoodsVO : goodsVOS) {
                String goodsName = searchGoodsVO.getGoodsName();
                String goodsIntro = searchGoodsVO.getGoodsIntro();
                //商品名称超长处理
                if(goodsName.length() > 30){
                    goodsName = goodsName.substring(0,30)+"...";
                    searchGoodsVO.setGoodsName(goodsName);
                }
                //商品介绍超长处理
                if(goodsIntro.length() > 50){
                    goodsIntro = goodsIntro.substring(0,50)+"...";
                    searchGoodsVO.setGoodsIntro(goodsIntro);
                }
            }
        }
        CommonPage page = new CommonPage(goodsVOS,total,pageUtil.getLimit(),pageUtil.getPage());
        return page;
    }
}
