package tz.cn.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import tz.cn.dao.DetailDao;
import tz.cn.dao.OrderDao;
import tz.cn.pojo.TbOrder;
import tz.cn.pojo.TbOrderDetail;
import tz.cn.service.DetailService;
import tz.cn.vo.OrderDetailVO;
import tz.cn.vo.PageData;
@Service
public class OrderDetailImpl implements DetailService{
	@Autowired
	private DetailDao detailDao;
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public PageData findOrderDetails(Integer currentPage, Integer pageSize,String orderCode) {
		//通过订单的编号查找订单id
		Integer orderId=orderDao.findOrderIdByCode(orderCode);
		//1.查询总条数
		int rowCount=detailDao.getRowCount(orderId);
		//2.计算出起始页
		int startIndex=(currentPage-1)*pageSize;
		//3.查询出分页数据
		List<TbOrderDetail> list=detailDao.findDetails(startIndex, pageSize,orderId);
		//4.封装数据
		PageData data=new PageData();
		data.setCode(0);
		data.setMsg("ok");
		data.setCount(rowCount);
		data.setData(list);
		return data;
	}
	//添加订单信息
	@Override
	public int addorderDateial(OrderDetailVO vo) {
		//1.查询该订单是否存在
		String orderNumber = vo.getOrderNumber();
		TbOrder tborder=orderDao.findOrderIdByOrderNumber(orderNumber);
		if(StringUtils.isEmpty(tborder)){
			//如果为空直接插入
			TbOrder order=new TbOrder();
			order.setOrderNumber(orderNumber);			 	//订单编号
			order.setOrderStatus("未付款");				 	//订单状态
			order.setProductCount(1);					 	//数量
			order.setAddress(vo.getAddress());			 	//地址
			order.setOrderConsignee(vo.getOrderConsignee());//收件人姓名
			order.setCustomerId(vo.getCustomerId());	    //客户id
			order.setPhone(vo.getPhone());   			    //手机号码
			order.setOrderAmountTotal(vo.getInfoPrice());   //小计金额
			order.setProductAmountTotal(vo.getInfoPrice()); //实际金额
			orderDao.saveOrder(order);						//保存订单数据
			Integer orderId = order.getId();
			//插入订单详细信息
			saveOrderDetail(vo,orderId);
		}else {
			//存在，修改订单金额和数量
			double productAmountTotal=
					vo.getInfoPrice()+tborder.getProductAmountTotal();//小计金额
			double orderAmountTotal=
					vo.getInfoPrice()+tborder.getOrderAmountTotal();  //实际金额
			int productCount=tborder.getProductCount()+1;			  //商品的数量
			orderDao.updateOrder(productAmountTotal,orderAmountTotal,productCount,tborder.getId());
			
			//添加订单详细信息（添加之前，根据订单ID和商品ID查询记录是否存在？）
			TbOrderDetail od=detailDao.findObjectByOIdandPid(vo.getProductId(),tborder.getId());
			if(StringUtils.isEmpty(od)){
				//如果没有查询到该记录（直接添加）
				saveOrderDetail(vo,tborder.getId());
			}else {
				//如果存在，修改小计金额和数量
				double subtotal=vo.getInfoPrice()+od.getSubtotal();  //小计金额
				double orderDetailNumber=od.getOrderDetailNumber()+1;//详细订单数量
				detailDao.updateOrderDetail(subtotal,orderDetailNumber,od.getId());
			}
		}
		return 1;
	}
	//添加订单详细信息
	private void saveOrderDetail(OrderDetailVO vo, Integer orderId) {
		TbOrderDetail od=new TbOrderDetail();	
		od.setProductId(vo.getProductId());		 //商品id
		od.setOrderId(orderId);					 //订单id
		od.setProductName(vo.getProductName());  //商品名称
		od.setProductPrice(vo.getInfoPrice());   //商品价格
		od.setOrderDetailNumber(1);				 //数量
		od.setDiscountRate(vo.getInfoDiscount());//折扣
		od.setSubtotal(vo.getInfoPrice());		 //小计金额
		od.setProductMarque(vo.getInfoSpec());	 //规则(型号)
		detailDao.saveOrderDetail(od);
	}
	//删除详细订单
	@Override
	public int delOrderDetail(Integer id) {
		//删除详细订单的同时还要删除对应的订单信息
		//1.通过详细订单的id找到订单的id
		Integer orderId=detailDao.findOrderIdbyDetailId(id);
		//2.删除对应的订单信息
		orderDao.delorderinfoById(orderId);
		//3.删除订单详细信息
		detailDao.delOrderDetailById(id);
		return 1;
	}
	@Override
	public int delOrderDetails(Integer[] ids) {
		//1.删除前找到对应的订单Id
		List<Integer> orderIds=detailDao.findOrderIdbyDetailIds(ids);
		//2.删除对应的订单信息
		orderDao.delorderinfoById(orderIds.get(0));
		//3.批量删除订单详细信息
		detailDao.delOrderDetails(ids);
		return 1;
	}
}
