package tz.cn.dao;
import org.apache.ibatis.annotations.Param;
import tz.cn.pojo.TbCustomer;
public interface CustomerDao {
	
	//根据id查询用户信息
	TbCustomer findCustomerById(@Param("id") Integer id);
}
