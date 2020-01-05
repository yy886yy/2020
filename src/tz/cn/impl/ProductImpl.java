package tz.cn.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tz.cn.dao.ProductDao;
import tz.cn.pojo.TbProductInfo;
import tz.cn.service.ProductService;
import tz.cn.vo.PageData;
@Service
public class ProductImpl implements ProductService{
	@Autowired
	private ProductDao productDao;
	@Override
	public PageData findProductList(Integer currentPage, Integer pageSize) {
		//1.查询总条数
		int rowCount=productDao.getRowCount();
		//2.计算出起始页
		int startIndex=(currentPage-1)*pageSize;
		//3.查询商品分页数据
		List<TbProductInfo> list = productDao.findProducts(startIndex, pageSize);
		//4.封装数据
		PageData data=new PageData();
		data.setCode(0);
		data.setMsg("ok");
		data.setCount(rowCount);
		data.setData(list);
		return data;
	}
}
