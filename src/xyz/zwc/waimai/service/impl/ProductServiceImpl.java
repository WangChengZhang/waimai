package xyz.zwc.waimai.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.zwc.waimai.dao.ProductMapper;
import xyz.zwc.waimai.entity.Product;
import xyz.zwc.waimai.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductMapper productMapper;

	@Override
	public List<Product> selectBySales(int shopid) {
		// TODO Auto-generated method stub
		return productMapper.selectBySales(shopid);
	}

	@Override
	public List<Product> selectByScore(int shopid) {
		// TODO Auto-generated method stub
		return productMapper.selectByScore(shopid);
	}

	@Override
	public List<Product> selectByPrice(int shopid) {
		// TODO Auto-generated method stub
		return productMapper.selectByPrice(shopid);
	}

	@Override
	public Product selectById(int productid) {
		// TODO Auto-generated method stub
		return productMapper.selectById(productid);
	}

	@Override
	public int insert(Product product) {
		Product lastp = productMapper.selectLast();
		if (lastp == null) {
			product.setProductid(1);
		} else {
			product.setProductid(lastp.getProductid() + 1);
		}
		return productMapper.insert(product);
	}

	@Override
	public Product selectLast() {
		// TODO Auto-generated method stub
		return productMapper.selectLast();
	}

}
