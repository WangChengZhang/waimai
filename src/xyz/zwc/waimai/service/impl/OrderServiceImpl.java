package xyz.zwc.waimai.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.zwc.waimai.dao.OrderItemMapper;
import xyz.zwc.waimai.dao.OrderMapper;
import xyz.zwc.waimai.entity.Order;
import xyz.zwc.waimai.entity.OrderItem;
import xyz.zwc.waimai.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderMapper orderMapper;
	@Autowired
	OrderItemMapper orderItemMapper;

	@Override
	public Order selectLast() {
		// TODO Auto-generated method stub
		return orderMapper.selectLast();
	}

	@Override
	public int insert(Order order) {

		int result = 0;
		if (this.selectLast() != null) {
			order.setOrderid(this.selectLast().getOrderid() + 1);
		} else {
			order.setOrderid(1);
		}
		result = orderMapper.insert(order);
		orderMapper.updateShopSale(order.getFkShopsShopid(), 1);
		Iterator<OrderItem> iterator = order.getOrderItems().iterator();
		while (iterator.hasNext()) {
			OrderItem orderItem = iterator.next();
			orderItem.setFkOrdersOrderid(order.getOrderid());
			orderItemMapper.insert(orderItem);
			orderMapper.updateProductSale(orderItem.getFkProductsProductid(), orderItem.getNumber());
		}

		return result;
	}

	@Override
	public int changeState(int orderState, int orderid) {
		// TODO Auto-generated method stub
		return orderMapper.changeState(orderState, orderid);
	}

	@Override
	public List<Order> selectByUserid(int userid) {
		// TODO Auto-generated method stub
		return orderMapper.selectByUserid(userid);
	}

	@Override
	public Order selectByOrderid(int orderid) {
		// TODO Auto-generated method stub
		return orderMapper.selectByOrderid(orderid);
	}

}
