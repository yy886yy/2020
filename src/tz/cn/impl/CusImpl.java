package tz.cn.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tz.cn.dao.CusDao;
import tz.cn.pojo.TbCustomer;
import tz.cn.service.CusService;
@Service
public class CusImpl implements CusService{
	@Autowired
	private CusDao cusDao;
	@Override
	public List<Map<String, Object>> findStatistics() {
		return cusDao.findStatistics();
	}
	@Override
	public List<TbCustomer> findCustomerNameByStr(String name) {
		return cusDao.findCustomerNameByStr(name);
	}

}
