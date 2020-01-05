package tz.cn.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import tz.cn.pojo.TbArea;
import tz.cn.pojo.TbCity;
import tz.cn.pojo.TbProvince;
public interface ProDao {
	//查询出所有省的数据
	List<TbProvince> findProvince();
	//根据省级code查询出市级信息
	List<TbCity> findCityByCode(@Param("provincecode")String provincecode);
	//查询区县级信息
	List<TbArea> findAreaByCode(@Param("citycode") String citycode);
	
}	
