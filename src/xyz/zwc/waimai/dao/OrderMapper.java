package xyz.zwc.waimai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import xyz.zwc.waimai.entity.Order;

public interface OrderMapper extends Mapper<Order> {
	public Order selectLast();

	public int changeState(@Param("orderstate") int orderState, @Param("orderid") int orderid);

	public List<Order> selectByUserid(@Param("userid") int userid);

	public int updateShopSale(@Param("shopid") int shopid, @Param("sale") int sale);

	public int updateProductSale(@Param("productid") int productid, @Param("sale") int sale);

	public Order selectByOrderid(@Param("orderid") int orderid);
}
