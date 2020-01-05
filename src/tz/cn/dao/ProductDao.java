package tz.cn.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import tz.cn.pojo.TbProductInfo;
public interface ProductDao {
	//查询商品总条数
	int getRowCount();
	//查询商品信息
	List<TbProductInfo> findProducts(@Param("startIndex") Integer startIndex,
									 @Param("pageSize") Integer pageSize);
}
