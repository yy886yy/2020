package tz.cn.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tz.cn.dao.UserDao;
import tz.cn.pojo.TbUserinfo;
import tz.cn.service.UserService;
import tz.cn.vo.PageData;
@Service
public class UserImpl implements UserService{
	@Autowired
	private UserDao userDao;
	@Override
	public PageData findObjects(Integer currentPage, Integer pageSize,String userName) {
		//1.查询总记录数
		int rowCount = userDao.getRowCount(userName);
		//2.根据当前页计算出起始页
		int startIndex=(currentPage-1)*pageSize;
		//3.查询分页信息记录
		List<TbUserinfo> list = userDao.findObjects(startIndex, pageSize,userName);
		//4.封装数据
		PageData pageData=new PageData();
		pageData.setCode(0);
		pageData.setMsg("ok");
		pageData.setCount(rowCount);
		pageData.setData(list);
		return pageData;
	}
	@Override
	public void updateStateById(Integer id, int state) {
		userDao.updateStateById(id,state);
	}
	@Override
	public void delUserInfoById(Integer id) {
		userDao.delUserInfoById(id);
	}
	@Override
	public int saveUserInfo(TbUserinfo user) {
		return userDao.saveUserInfo(user);
	}
	@Override
	public int delUserInfoByIds(Integer[] ids) {
		return userDao.delUserInfoByIds(ids);
	}
	@Override
	public TbUserinfo findUserinfoById(Integer id) {
		return userDao.findUserinfoById(id);
	}
	@Override
	public int updateUserinfo(TbUserinfo userinfo) {
		
		return userDao.updateUserinfo(userinfo);
	}
	@Override
	public TbUserinfo findUserByNameAndPwd(String username, String password) {
	 TbUserinfo user=userDao.findUserByNameAndPwd(username,password);
		return user;
	}

}
