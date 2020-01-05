package tz.cn.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tz.cn.dao.ProDao;
import tz.cn.pojo.TbArea;
import tz.cn.pojo.TbCity;
import tz.cn.pojo.TbProvince;
import tz.cn.service.ProService;
@Service
public class ProImpl implements ProService{
	@Autowired
	private ProDao proDao;
	//查询出所有省的数据
	@Override
	public List<TbProvince> findProvince() {
		return proDao.findProvince();
	}
	//根据省级code查询出市级信息
	@Override
	public List<TbCity> findCityByCode(String provincecode) {
		return proDao.findCityByCode(provincecode);
	}
	@Override
	public List<TbArea> findAreaByCode(String citycode) {
		return proDao.findAreaByCode(citycode);
	}
}
