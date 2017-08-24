package xyz.zwc.waimai.service;

import java.util.List;

import xyz.zwc.waimai.entity.CustAddress;

public interface CustAddressService {
	public List<CustAddress> selectByUserid(int userid);

	public CustAddress selectLast();

	public int insert(CustAddress custAddress);

	public int update(CustAddress custAddress);

	public int delete(CustAddress custAddress);

	public CustAddress select(CustAddress custAddress);
}
