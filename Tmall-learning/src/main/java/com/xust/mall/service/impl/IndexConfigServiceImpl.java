package com.xust.mall.service.impl;

import com.xust.mall.common.CommonPage;
import com.xust.mall.common.enums.ServiceResultEnum;
import com.xust.mall.common.utils.BeanUtil;
import com.xust.mall.common.utils.PageUtil;
import com.xust.mall.mapper.GoodsMapper;
import com.xust.mall.mapper.IndexConfigMapper;
import com.xust.mall.model.Goods;
import com.xust.mall.model.IndexConfig;
import com.xust.mall.service.IndexConfigService;
import com.xust.mall.vo.IndexGoodsConfigVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndexConfigServiceImpl implements IndexConfigService {
    @Autowired
    private IndexConfigMapper indexConfigMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public CommonPage getConfigPage(PageUtil pageUtil) {
        List<IndexConfig> indexConfigs = indexConfigMapper.selectIndexConfigs(pageUtil);
        int total = indexConfigMapper.getTotalIndexConfigs(pageUtil);
        CommonPage page = new CommonPage(indexConfigs,total,pageUtil.getLimit(),pageUtil.getPage());
        return page;
    }

    @Override
    public String saveIndexConfig(IndexConfig indexConfig) {
        if(indexConfigMapper.insertSelective(indexConfig) > 0){
            return ServiceResultEnum.SUCCESS.getMessage();
        }
        return ServiceResultEnum.DB_ERROR.getMessage();
    }

    @Override
    public String updateIndexConfig(IndexConfig indexConfig) {
        IndexConfig config = indexConfigMapper.selectByPrimaryKey(indexConfig.getConfigId());
        if(config == null){
            return ServiceResultEnum.DATA_NOT_EXIST.getMessage();
        }
        if(indexConfigMapper.updateByPrimaryKeySelective(indexConfig) > 0){
            return ServiceResultEnum.SUCCESS.getMessage();
        }
        return ServiceResultEnum.DB_ERROR.getMessage();
    }

    @Override
    public IndexConfig getIndexConfig(Long id) {
        return indexConfigMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<IndexGoodsConfigVO> getConfigGoodsForIndex(int configType, int number) {
        List<IndexGoodsConfigVO> indexConfigVOS = new ArrayList<>(number);
        List<IndexConfig> configs = indexConfigMapper.selectIndexConfigByTypeAndNum(configType, number);
        if (!CollectionUtils.isEmpty(configs)) {
            //取出所有的goodsId
            List<Long> goodsIds = configs.stream().map(IndexConfig::getGoodsId).collect(Collectors.toList());
            List<Goods> goods = goodsMapper.selectByPrimaryKeys(goodsIds);
            indexConfigVOS = BeanUtil.copyList(goods, IndexGoodsConfigVO.class);
            for (IndexGoodsConfigVO indexConfigVO : indexConfigVOS) {
                String goodsName = indexConfigVO.getGoodsName();
                String goodsIntro = indexConfigVO.getGoodsIntro();
                // 字符串过长导致文字超出的问题
                if (goodsName.length() > 30) {
                    goodsName = goodsName.substring(0, 30) + "...";
                    indexConfigVO.setGoodsName(goodsName);
                }
                if (goodsIntro.length() > 20) {
                    goodsIntro = goodsIntro.substring(0, 20) + "...";
                    indexConfigVO.setGoodsIntro(goodsIntro);
                }
            }
        }
        return indexConfigVOS;
    }

    @Override
    public Boolean deleteBatch(Long[] ids) {
        if(ids.length < 1){
            return false;
        }
        return indexConfigMapper.deleteBatch(ids) > 0;
    }
}
