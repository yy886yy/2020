package tz.cn.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tz.cn.service.DetailService;
import tz.cn.vo.OrderDetailVO;
import tz.cn.vo.PageData;
@Controller
@RequestMapping("/detail")
public class OrderDetailController {
	@Autowired
	private DetailService detailService;
	//查询订单详细分页信息
	@RequestMapping("/findOrderDetails")
	@ResponseBody
	public PageData findOrderDetails(Integer page,Integer limit,String orderCode) {
		return detailService.findOrderDetails(page, limit,orderCode);
	}
	
	//添加订单信息
	@RequestMapping(value="/addOrderDetail",method=RequestMethod.POST)
	@ResponseBody
	public String addOrderDetail(@RequestBody OrderDetailVO orderDetailVO) {
		String result="error";
		int count=detailService.addorderDateial(orderDetailVO);
		if(count>0) {
			result="ok";
		}
		return result;
	}
	//根据订单详细Id删除订单
	@RequestMapping("/delOrderDetail")
	@ResponseBody
	public String delOrderDetail(Integer id){
		String result="error";
		int count=detailService.delOrderDetail(id);
		if(count>0) {
			result="ok";
		}
		return result;
	}
	//批量删除订单
	@RequestMapping("/delOrderDetails")
	@ResponseBody
	public String delOrderDetails(Integer[] ids) {
		String result="error";
		if(ids.length==0||ids==null){
			return result;
		}
		int count=detailService.delOrderDetails(ids);
		if(count>0) {
			result="ok";
		}
		System.out.println(result);
		return result;
	}
	
}
