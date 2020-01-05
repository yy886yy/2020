package tz.cn.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tz.cn.pojo.TbCustomer;
import tz.cn.service.CusService;
@Controller
@RequestMapping("/customer")
public class CustomerController {
	private final Logger logger=Logger.getLogger(CustomerController.class); 
	@Autowired
	private CusService cusService;
	@RequestMapping("/returnStatistics")
	public String returnStatistics() {
		return "customer/statistics";
	}
	//根据输入的字符模糊查询姓名
	@RequestMapping("/getCustomerNameByStr")
	@ResponseBody
	public List<TbCustomer> getCustomerNameByStr(String name) {
		return cusService.findCustomerNameByStr(name);
	}
	
	@RequestMapping("/statistics")
	@ResponseBody
	public List<Map<String,Object>> statistics() {
		return cusService.findStatistics();
	}
}
