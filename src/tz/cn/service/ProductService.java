package tz.cn.service;

import tz.cn.vo.PageData;

public interface ProductService {
	//查询pageData数据
	PageData findProductList(Integer currentPage,Integer pageSize); 
}
