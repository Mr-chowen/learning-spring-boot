package com.converter;

import org.springframework.core.convert.converter.Converter;

import com.po.Goods;

public class GoodsConverter implements Converter<String, Goods> {

	@Override
	public Goods convert(String source) {
		Goods goods=new Goods();
		String stringValues[]=source.split(",");
		if (stringValues!=null && stringValues.length==3) {
			goods.setGoodsname(stringValues[0]);
			goods.setGoodsnumber(Integer.parseInt(stringValues[2]));
			goods.setGoodsprice(Double.parseDouble(stringValues[1]));
			return goods;
		} else {
			throw new IllegalArgumentException(String.format("类型转换失败，需要格式'apple,20.5,200',但是格式是[%s]", source));
		}
	}

}
