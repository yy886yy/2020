package tz.cn.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import tz.cn.pojo.TbOrderDetail;
public interface DetailDao {
	//查询总条数
	int getRowCount(@Param("orderId") Integer orderId);
	//查询分页信息数据
	List<TbOrderDetail> findDetails(@Param("startIndex") Integer startIndex,
								   @Param("pageSize") Integer pageSize,
								   @Param("orderId") Integer orderId);
	//添加订单的详细信息
	void saveOrderDetail(TbOrderDetail od);
	//根据商品ID和
	TbOrderDetail findObjectByOIdandPid(@Param("productId") Integer productId,
										@Param("orderId") Integer orderId);
	//修改详细订单的数量和小计金额
	void updateOrderDetail(@Param("subtotal") Double subtotal, 
						   @Param("orderDetailNumber") Double orderDetailNumber,
						   @Param("id") Integer id);
	//根据id查询订单id
	Integer findOrderIdbyDetailId(@Param("id") Integer id);
	//根据Id删除数据
	void delOrderDetailById(@Param("id") Integer id);
	//找到对应的订单id
	List<Integer> findOrderIdbyDetailIds(@Param("ids") Integer[] ids);
	//批量删除详细订单
	void delOrderDetails(@Param("ids") Integer[] ids);
}
