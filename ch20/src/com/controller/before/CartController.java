package com.controller.before;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.before.CartService;

@Controller
@RequestMapping("/cart")
public class CartController extends BaseBeforeController {
	@Autowired
	private CartService cartService;
	/*
	 * 	关注
	 */
	@RequestMapping("/focus")
	public String focus(Model model,Integer id,HttpSession session) {
		return cartService.focus(model, id, session);
	}
	/*
	 *	 添加
	 */
	@RequestMapping("/putCart")
	public String putCart(Model model,Integer shoppingnum,Integer id,HttpSession session) {
		return cartService.putCart(model, shoppingnum, id, session);
	}
	/*
	 * 	查询
	 */
	@RequestMapping("/selectCart")
	public String selectCart(Model model,HttpSession session) {
		return cartService.selectCart(model, session);
	}
	/*
	 * 	删除
	 */
	@RequestMapping("/deleteAgoods")
	public String deleteAgoods(Integer id,HttpSession session) {
		return cartService.deleteAgoods(id, session);
	}
	/*
	 * 	清空
	 */
	@RequestMapping("/clear")
	public String clear(HttpSession session) {
		return cartService.clear(session);
	}
	/*
	 * 	结算
	 */
	@RequestMapping("/orderConfirm")
	public String orderCinfirm(Model model,HttpSession session) {
		return cartService.orderConfirm(model, session);
	}

}
