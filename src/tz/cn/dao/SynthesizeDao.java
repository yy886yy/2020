package tz.cn.dao;
import java.util.List;
import java.util.Map;
public interface SynthesizeDao {
	//查询出年销售数据
	List<Map<String,Object>> findYear();
	//查询出季度销售数据
	List<Map<String,Object>> findQuarter();
	//查询出月销售数据
	List<Map<String,Object>> findMonth();
}
