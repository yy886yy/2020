package tz.cn.service;
import java.util.List;
import tz.cn.pojo.TbArea;
import tz.cn.pojo.TbCity;
import tz.cn.pojo.TbProvince;
public interface ProService {
	//查询出所有省的数据
	List<TbProvince> findProvince();
	//根据省级code查询出市级信息
	List<TbCity> findCityByCode(String provincecode);
	//查询区县级信息
	List<TbArea> findAreaByCode(String citycode);
}
