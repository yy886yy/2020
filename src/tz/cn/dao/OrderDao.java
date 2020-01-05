package tz.cn.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.sun.org.glassfish.gmbal.ParameterNames;

import tz.cn.pojo.TbOrder;
import tz.cn.vo.OrderVO;
public interface OrderDao {
	//查询总记录数
	int getRowCount(@Param("customerName") String customerName,
					@Param("orderlogisticsId")String orderlogisticsId,
					@Param("orderStatus")String orderStatus);
	//查询分页数据
	List<OrderVO> findOrderList(@Param("startIndex") Integer startIndex,
								@Param("pageSize") Integer pageSize,
								@Param("customerName") String customerName,
								@Param("orderlogisticsId")String orderlogisticsId,
								@Param("orderStatus")String orderStatus);
	//根据Id删除订单
	int delorderinfoById(@Param("id") Integer id);
	//批量删除订单
	int delorderinfoByIds(@Param("ids") Integer[] ids);
	//根据订单编号查询订单id
	Integer findOrderIdByCode(@Param("orderCode") String orderCode);
	//根据订单编号查询该订单是否存在
	TbOrder findOrderIdByOrderNumber(@Param("orderNumber") String orderNumber);
	//根据ID修改小计金额和实际金额
	void updateOrder(@Param("productAmountTotal") double productAmountTotal, 
			         @Param("orderAmountTotal") double orderAmountTotal,
			         @Param("productCount") Integer productCount,
			         @Param("id") Integer id);
	//添加订单信息
	void saveOrder(TbOrder order);
	
}
