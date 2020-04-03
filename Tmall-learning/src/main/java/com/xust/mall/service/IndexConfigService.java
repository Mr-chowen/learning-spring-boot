package com.xust.mall.service;

import com.xust.mall.common.CommonPage;
import com.xust.mall.common.utils.PageUtil;
import com.xust.mall.model.IndexConfig;
import com.xust.mall.vo.IndexGoodsConfigVO;

import java.util.List;

public interface IndexConfigService {
    CommonPage getConfigPage(PageUtil pageUtil);

    String saveIndexConfig(IndexConfig indexConfig);

    String updateIndexConfig(IndexConfig indexConfig);

    IndexConfig getIndexConfig(Long id);

    List<IndexGoodsConfigVO> getConfigGoodsForIndex(int configType, int number);

    Boolean deleteBatch(Long[] ids);
}
