package tz.cn.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tz.cn.service.SynthesizeService;
@Controller
@RequestMapping("/statistics")
public class StatisticsController {
	@Autowired
	private SynthesizeService service;
	//跳转到综合统计页面
	@RequestMapping("/returnSynthesize")
	public String returnSynthesize() {
		return "customer/synthesize";
	}
	//跳转到年统计页面
	@RequestMapping("/returnYear")
	public String returnYear() {
		return "customer/year";
	}
	//跳转到季度统计页面
	@RequestMapping("/returnQuarter")
	public String returnQuarter() {
		return "customer/quarter";
	}
	//跳转到月统计页面
	@RequestMapping("/returnMonth")
	public String returnMonth() {
		return "customer/month";
	}
	//查询出年销售额的数据
	@RequestMapping("/findYear")
	@ResponseBody
	public List<Map<String,Object>> findYear() {
		return service.findYear();
	}
	//查询出季度销售额的数据
	@RequestMapping("/findQuarter")
	@ResponseBody
	public List<Map<String,Object>> findQuarter() {
		return service.findQuarter();
	}
	//查询出月销售额的数据
	@RequestMapping("/findMonth")
	@ResponseBody
	public List<Map<String,Object>> findMonth() {
		return service.findMonth();
	}
}
