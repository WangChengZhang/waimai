package xyz.zwc.waimai.service;

import java.math.BigDecimal;

import xyz.zwc.waimai.entity.Comment;
import xyz.zwc.waimai.entity.Order;
import xyz.zwc.waimai.entity.Page;

public interface CommentService {
	public Page<Comment> selectPage(Page<Comment> page, int shopid);

	public Page<Comment> selectCount(Page<Comment> page, int shopid);

	public int addComment(Order order, String comment, BigDecimal attitudescore, BigDecimal dishscore);
}
