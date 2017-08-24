package xyz.zwc.waimai.service.impl;

import java.math.BigDecimal;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.zwc.waimai.dao.CommentMapper;
import xyz.zwc.waimai.dao.OrderMapper;
import xyz.zwc.waimai.dao.ProductMapper;
import xyz.zwc.waimai.dao.ShopMapper;
import xyz.zwc.waimai.entity.Comment;
import xyz.zwc.waimai.entity.Order;
import xyz.zwc.waimai.entity.OrderItem;
import xyz.zwc.waimai.entity.Page;
import xyz.zwc.waimai.entity.Product;
import xyz.zwc.waimai.entity.Shop;
import xyz.zwc.waimai.service.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private ProductMapper productMapper;

	public int updateShopScore(int shopid, BigDecimal attitudescore, BigDecimal dishscore) {
		int result = 0;
		Shop shop = shopMapper.selectById(shopid);
		attitudescore = shop.getAttitudeScore().multiply(new BigDecimal(shop.getMonthlySale() - 1)).add(attitudescore)
				.divide(new BigDecimal(shop.getMonthlySale()), 2, BigDecimal.ROUND_HALF_UP);
		dishscore = shop.getDishScore().multiply(new BigDecimal(shop.getMonthlySale() - 1)).add(dishscore)
				.divide(new BigDecimal(shop.getMonthlySale()), 2, BigDecimal.ROUND_HALF_UP);
		result = shopMapper.updateScore(attitudescore, dishscore, shopid);
		return result;
	}

	public int updatePScore(int productid, int number, BigDecimal attitudescore, BigDecimal dishscore) {
		int result = 0;
		Product product = productMapper.selectById(productid);
		attitudescore = product.getAttitudeScore().multiply(new BigDecimal(product.getMonthlySale() - number))
				.add(attitudescore.multiply(new BigDecimal(number)))
				.divide(new BigDecimal(product.getMonthlySale()), 2, BigDecimal.ROUND_HALF_UP);
		dishscore = product.getDishScore().multiply(new BigDecimal(product.getMonthlySale() - number))
				.add(dishscore.multiply(new BigDecimal(number)))
				.divide(new BigDecimal(product.getMonthlySale()), 2, BigDecimal.ROUND_HALF_UP);
		result = productMapper.updateScore(productid, attitudescore, dishscore);
		return result;
	}

	@Override
	public Page<Comment> selectPage(Page<Comment> page, int shopid) {
		page.setResults(commentMapper.selectPage(shopid, page.getStartPoint(), page.getPageSize()));
		return page;
	}

	@Override
	public Page<Comment> selectCount(Page<Comment> page, int shopid) {
		page.setTotalRecords(commentMapper.selectCount(shopid));
		return page;
	}

	@Override
	public int addComment(Order order, String comment, BigDecimal attitudescore, BigDecimal dishscore) {
		int result = 0;
		orderMapper.changeState(2, order.getOrderid());
		this.updateShopScore(order.getFkShopsShopid(), attitudescore, dishscore);
		Iterator<OrderItem> it = order.getOrderItems().iterator();
		while (it.hasNext()) {
			OrderItem oi = it.next();
			this.updatePScore(oi.getFkProductsProductid(), oi.getNumber(), attitudescore, dishscore);
			Comment com = new Comment();
			com.setAttitudeScore(attitudescore);
			com.setDishScore(dishscore);
			com.setComment(comment);
			com.setFkOrdersOrderid(order.getOrderid());
			com.setFkProductsProductid(oi.getFkProductsProductid());
			com.setFkUsersUserid(order.getFkUserUserid());
			result = commentMapper.insert(com);
		}
		return result;
	}

}
