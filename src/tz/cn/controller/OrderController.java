package tz.cn.controller;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tz.cn.service.OrderService;
import tz.cn.utils.Logistics;
import tz.cn.utils.OrderCoderUtil;
import tz.cn.vo.PageData;
@Controller
@RequestMapping("/order")
public class OrderController {
	private final Logger logger=Logger.getLogger(OrderController.class);
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/returnOrderList")
	public String returnOrderList() {
		return "order/orderList";
	}
	
	//跳转到新增订单页面
	@RequestMapping("/returnAddOrder")
	public String returnAddOrder() {
		return "order/addOrder";
	}
	//获取订单编号
	@RequestMapping("/getOrderCode")
	@ResponseBody
	public Map<String,String> getOrderCode() {
		String code = OrderCoderUtil.getOrderCode(null);
		Map<String,String> map=new HashMap<>();
		map.put("code",code);
		return map;
	}
	
	//获取分页数据
	@RequestMapping("/findUserList")
	@ResponseBody
	public PageData findUserList(Integer page,Integer limit,String customerName,
								String orderlogisticsId,String orderStatus) {
		return orderService.findOrderVO(page, limit,customerName,orderlogisticsId,orderStatus);
	}
	//根据Id删除订单
	@RequestMapping("/delorderinfoById")
	@ResponseBody
	public String delorderinfoById(Integer id) {
		String result="0";
		int count = orderService.delorderinfoById(id);
		if(count>0){
			result="1";
		}
		return result;
	}
	//批量删除订单
	@RequestMapping("/delorderinfoByIds")
	@ResponseBody
	public String delorderinfoByIds(Integer[] ids) {
		String result="0";
		int count = orderService.delorderinfoByIds(ids);
		if(count>0) {
			result="1";
		}
		return result;
	}
	//跳转到物流页面
	@RequestMapping("/findOrderlogisticsId")
	public ModelAndView findOrderlogisticsId(String orderlogisticsId) {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("order/logistics");
		mv.addObject("orderlogisticsId",orderlogisticsId);
		return mv;
	}
	
	//查询订单号
	@RequestMapping(value="/findOrderlogistics",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String findOrderlogistics(String orderlogistics,String orderlogisticsId) {
		String info = Logistics.getLogisticsContent(orderlogistics,orderlogisticsId);
		return info;
	}
}
