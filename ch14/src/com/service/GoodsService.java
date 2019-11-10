package com.service;
import java.util.ArrayList;

import com.po.Goods;
public interface GoodsService {
	boolean save(Goods g);
	ArrayList<Goods> getGoods();
}
