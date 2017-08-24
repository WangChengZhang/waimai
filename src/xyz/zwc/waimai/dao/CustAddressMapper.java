package xyz.zwc.waimai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import xyz.zwc.waimai.entity.CustAddress;

public interface CustAddressMapper extends Mapper<CustAddress> {
	public List<CustAddress> selectByUserid(@Param("userid") int userid);

	public CustAddress selectLast();
}
