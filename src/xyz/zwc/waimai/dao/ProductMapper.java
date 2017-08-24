package xyz.zwc.waimai.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import xyz.zwc.waimai.entity.Product;

public interface ProductMapper extends Mapper<Product> {
	public List<Product> selectBySales(@Param("shopid") int shopid);

	public List<Product> selectByScore(@Param("shopid") int shopid);

	public List<Product> selectByPrice(@Param("shopid") int shopid);

	public Product selectById(@Param("productid") int productid);

	public int updateScore(@Param("productid") int productid, @Param("attitudescore") BigDecimal attitudescore,
			@Param("dishscore") BigDecimal dishscore);

	public Product selectLast();
}
