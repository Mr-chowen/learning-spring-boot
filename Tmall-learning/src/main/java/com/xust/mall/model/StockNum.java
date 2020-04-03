package com.xust.mall.model;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
public class StockNum {
    private Long goodsId;

    private Integer goodsCount;
}
