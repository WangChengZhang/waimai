package xyz.zwc.waimai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import xyz.zwc.waimai.entity.Comment;

public interface CommentMapper extends Mapper<Comment> {
	public List<Comment> selectPage(@Param("shopid") int shopid, @Param("startpoint") int startpoint,
			@Param("pagesize") int pagesize);

	public Integer selectCount(@Param("shopid") int shopid);
}
