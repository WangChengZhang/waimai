package xyz.zwc.waimai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.zwc.waimai.dao.ProductCatMapper;
import xyz.zwc.waimai.entity.ProductCat;
import xyz.zwc.waimai.service.ProductCatService;

@Service("productCatService")
public class ProductCatServiceImpl implements ProductCatService {

	@Autowired
	private ProductCatMapper productCatMapper;

	@Override
	public int insert(ProductCat productCat) {
		ProductCat lastp = productCatMapper.selectLast();
		if (lastp == null) {
			productCat.setPdcatid(1);
		} else {
			productCat.setPdcatid(lastp.getPdcatid() + 1);
		}
		return productCatMapper.insert(productCat);
	}

}
