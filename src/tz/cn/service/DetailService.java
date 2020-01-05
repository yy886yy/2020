package tz.cn.service;
import tz.cn.vo.OrderDetailVO;
import tz.cn.vo.PageData;
public interface DetailService {
	//查询pageData的数据
	PageData findOrderDetails(Integer currentPage,Integer pageSize,String orderCode);
	//添加订单信息
	int addorderDateial(OrderDetailVO orderDetailVO);
	//根据订单详细id删除数据
	int delOrderDetail(Integer id);
	//批量删除订单数据
	int delOrderDetails(Integer[] ids); 
}
