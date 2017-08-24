package xyz.zwc.waimai.dao;

import java.util.List;

import xyz.zwc.waimai.entity.Page;

public interface Mapper<T> {
	public int insert(T entity);

	public int update(T entity);

	public int delete(T entity);
	
	public int deleteList(String[] pks);//通过主键批量删除

	public T select(T entity);

	public List<T> selectPages(Page<T> page);// 查询分页

	public Integer selectPageCount(Page<T> page);// 查询记录数
}
