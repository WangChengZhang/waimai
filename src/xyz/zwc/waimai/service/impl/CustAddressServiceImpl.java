package xyz.zwc.waimai.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.zwc.waimai.dao.CustAddressMapper;
import xyz.zwc.waimai.entity.CustAddress;
import xyz.zwc.waimai.service.CustAddressService;

@Service("custAddressService")
public class CustAddressServiceImpl implements CustAddressService {

	@Autowired
	private CustAddressMapper custAddressMapper;

	@Override
	public List<CustAddress> selectByUserid(int userid) {
		// TODO Auto-generated method stub
		return custAddressMapper.selectByUserid(userid);
	}

	@Override
	public CustAddress selectLast() {
		// TODO Auto-generated method stub
		return custAddressMapper.selectLast();
	}

	@Override
	public int insert(CustAddress custAddress) {
		CustAddress lastadd = this.selectLast();
		if (lastadd != null) {
			int flag = 0;
			custAddress.setCustaddid(lastadd.getCustaddid() + 1);
			flag = custAddressMapper.insert(custAddress);
			while (flag == 0) {
				custAddress.setCustaddid(custAddress.getCustaddid() + 1);
				flag = custAddressMapper.insert(custAddress);
			}
			return flag;
		} else {
			custAddress.setCustaddid(1);
			return custAddressMapper.insert(custAddress);
		}
	}

	@Override
	public int update(CustAddress custAddress) {
		// TODO Auto-generated method stub
		return custAddressMapper.update(custAddress);
	}

	@Override
	public int delete(CustAddress custAddress) {
		// TODO Auto-generated method stub
		return custAddressMapper.delete(custAddress);
	}

	@Override
	public CustAddress select(CustAddress custAddress) {
		// TODO Auto-generated method stub
		return custAddressMapper.select(custAddress);
	}

}
