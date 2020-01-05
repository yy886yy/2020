package tz.cn.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tz.cn.service.ProductService;
import tz.cn.vo.PageData;
@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService service;
	
	@RequestMapping("/returnProductList")
	public String returnProductList() {
		return "product/productList";
	}
	@RequestMapping("/findProducts")
	@ResponseBody
	public PageData findProducts() {
		PageData data=new PageData();
		return data;
	}
	//查询商品信息
	@RequestMapping("/findProductList")
	@ResponseBody
	public PageData findProductList(Integer page,Integer limit) {
		return service.findProductList(page, limit);
	}
}
