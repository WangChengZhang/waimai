package xyz.zwc.waimai.entity.page;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

public class Basket {
	private Integer count;// 物品总数
	private String totalPrice;// 总价
	private List<ProductInfo> productList;// 购物车所有物品

	// 更新物品总价总数
	public void refresh() {
		if (this.productList != null) {
			this.count = 0;
			this.totalPrice = "0";
			Iterator<ProductInfo> iterator = this.productList.iterator();
			while (iterator.hasNext()) {
				ProductInfo product = iterator.next();
				// 单件物品最多9999个
				if (product.getCount() > 9999) {
					product.setCount(9999);
				}
				this.count = count + product.getCount();
				product.setPrice(product.getUnitPrice().multiply(new BigDecimal(product.getCount())).toString());
				this.totalPrice = (new BigDecimal(totalPrice)).add(new BigDecimal(product.getPrice())).toString();
				// 移除数量为0的商品
				if (product.getCount() <= 0) {
					iterator.remove();
				}
			}
		}
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<ProductInfo> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductInfo> productList) {
		this.productList = productList;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

}
