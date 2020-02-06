package com.xust.dao;

import com.xust.nosql.elasticsearch.document.EsProduct;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 搜索系统中的商品管理自定义Dao
 */
@Component
public interface EsProductDao {
    List<EsProduct> getAllEsProductList(@Param("id")Long id);
}
