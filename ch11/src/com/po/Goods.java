package com.po;

public class Goods {
	private String goodsname;
	private double goodsprice;
	private int goodsnumber;
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public double getGoodsprice() {
		return goodsprice;
	}
	public void setGoodsprice(double goodsprice) {
		this.goodsprice = goodsprice;
	}
	public int getGoodsnumber() {
		return goodsnumber;
	}
	public void setGoodsnumber(int goodsnumber) {
		this.goodsnumber = goodsnumber;
	}
	@Override
	public String toString() {
		return "Goods [goodsname=" + goodsname + ", goodsprice=" + goodsprice + ", goodsnumber=" + goodsnumber + "]";
	}
	
}
