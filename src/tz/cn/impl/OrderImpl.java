package tz.cn.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tz.cn.dao.OrderDao;
import tz.cn.service.OrderService;
import tz.cn.vo.OrderVO;
import tz.cn.vo.PageData;
@Service
public class OrderImpl implements OrderService{
	@Autowired
	private OrderDao orderDao;
	@Override
	public PageData findOrderVO(Integer cureentPage, Integer pageSize,String customerName,
			String orderlogisticsId,String orderStatus) {
		//1.查询总条数
		int rowCount = orderDao.getRowCount(customerName,orderlogisticsId,orderStatus);
		//2.计算出起始页
		int startIndex=(cureentPage-1)*pageSize;
		//3.查询分页数据
		List<OrderVO> orderList = orderDao.findOrderList(startIndex, pageSize,customerName,orderlogisticsId,orderStatus);
		//4.封装数据
		PageData data=new PageData();
		data.setCode(0);
		data.setMsg("ok");
		data.setCount(rowCount);
		data.setData(orderList);
		return data;
	}
	@Override
	public int delorderinfoById(Integer id) {
		return orderDao.delorderinfoById(id);
	}
	@Override
	public int delorderinfoByIds(Integer[] ids) {
		return orderDao.delorderinfoByIds(ids);
	}

}
