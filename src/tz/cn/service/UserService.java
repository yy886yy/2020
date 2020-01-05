package tz.cn.service;
import tz.cn.pojo.TbUserinfo;
import tz.cn.vo.PageData;
public interface UserService {
	//查询分页信息的数据
	PageData findObjects(Integer currentPage,Integer pageSize,String userName);
	//修改状态信息
	void updateStateById(Integer id, int state);
	//根据id删除用户信息
	void delUserInfoById(Integer id); 
	//批量删除用户信息
	int delUserInfoByIds(Integer[] ids);
	//添加管理信息
	int saveUserInfo(TbUserinfo user);
	//根据Id查询对应的信息
	TbUserinfo findUserinfoById(Integer id);
	//修改管理员信息
	int updateUserinfo(TbUserinfo userinfo);
	//根据用户名和密码查找用户是否存在
	TbUserinfo findUserByNameAndPwd(String username, String password);
}
