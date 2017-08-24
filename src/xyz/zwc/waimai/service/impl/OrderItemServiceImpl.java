package xyz.zwc.waimai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.zwc.waimai.dao.OrderItemMapper;
import xyz.zwc.waimai.entity.OrderItem;
import xyz.zwc.waimai.service.OrderItemService;

@Service("orderItemService")
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	OrderItemMapper orderItemMapper;

	@Override
	public int insert(OrderItem orderItem) {
		// TODO Auto-generated method stub
		return orderItemMapper.insert(orderItem);
	}

}
