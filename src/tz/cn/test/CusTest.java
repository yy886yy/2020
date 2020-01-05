package tz.cn.test;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tz.cn.dao.CusDao;
import tz.cn.dao.CustomerDao;
import tz.cn.dao.OrderDao;
import tz.cn.pojo.TbOrder;
import tz.cn.vo.OrderVO;
public class CusTest {
	@Test
	public void test() {
		ClassPathXmlApplicationContext ctc=new ClassPathXmlApplicationContext("configs/spring-configs.xml");
		OrderDao dao = ctc.getBean(OrderDao.class);
		List<OrderVO> list = dao.findOrderList(0,5,null,null,null);
		for(OrderVO vo:list) {
			System.out.println(vo.getCustomer());
		}
		
	}
}
