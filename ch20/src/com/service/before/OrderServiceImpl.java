package com.service.before;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.dao.before.OrderDao;
import com.po.Order;
import com.util.MyUtil;
@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;

	@Override
	public String orderSubmit(Model model, HttpSession session, Double amount) {
		// TODO Auto-generated method stub
		Order order=new Order();
		order.setAmount(amount);
		order.setBusertable_id(MyUtil.getUserId(session));
		//	生成订单，主键返回order
		orderDao.addOrder(order);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("ordersn", order.getId());
		map.put("uid", MyUtil.getUserId(session));
		orderDao.addOrderDetail(map);
		//	更新商品库存
		List<Map<String, Object>> list=orderDao.selectGoodsShop(MyUtil.getUserId(session));
		for (Map<String, Object> map2 : list) {
			orderDao.updateStore(map2);
		}
		orderDao.clear(MyUtil.getUserId(session));
		model.addAttribute("ordersn", order.getId());
		return "before/orderdone";
	}

	@Override
	public String pay(Integer ordersn) {
		// TODO Auto-generated method stub
		orderDao.pay(ordersn);
		return "before/paydone";
	}

}
