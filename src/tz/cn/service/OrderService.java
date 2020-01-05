package tz.cn.service;

import org.apache.ibatis.annotations.Param;

import tz.cn.vo.PageData;

public interface OrderService {
	//查询分页数据
	PageData findOrderVO(Integer cureentPage,Integer pageSize,String customerName,
			String orderlogisticsId,String orderStatus); 
	//根据Id删除订单
	int delorderinfoById(Integer id);
	//批量删除订单
	int delorderinfoByIds(@Param("ids") Integer[] ids);
}
