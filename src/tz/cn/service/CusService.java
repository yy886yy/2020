package tz.cn.service;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import tz.cn.pojo.TbCustomer;
public interface CusService {
	//查询统计数据
	List<Map<String,Object>> findStatistics();
	//根据输入的字符模糊查询对应的数据
	List<TbCustomer> findCustomerNameByStr(@Param("name") String name);
}
