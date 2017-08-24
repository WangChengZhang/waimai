package xyz.zwc.waimai.service;

import java.util.List;

import xyz.zwc.waimai.entity.Product;

public interface ProductService {
	public List<Product> selectBySales(int shopid);

	public List<Product> selectByScore(int shopid);

	public List<Product> selectByPrice(int shopid);

	public Product selectById(int productid);

	public int insert(Product product);

	public Product selectLast();
}
