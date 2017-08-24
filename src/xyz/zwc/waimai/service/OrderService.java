package xyz.zwc.waimai.service;

import java.util.List;

import xyz.zwc.waimai.entity.Order;

public interface OrderService {
	public Order selectLast();

	public int insert(Order order);

	public int changeState(int orderState, int orderid);

	public List<Order> selectByUserid(int userid);

	public Order selectByOrderid(int orderid);
}
