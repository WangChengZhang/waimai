package xyz.zwc.waimai.dao;

import xyz.zwc.waimai.entity.ProductCat;

public interface ProductCatMapper extends Mapper<ProductCat> {
	public ProductCat selectLast();
}
