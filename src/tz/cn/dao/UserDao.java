package tz.cn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sun.org.glassfish.gmbal.ParameterNames;

import tz.cn.pojo.TbUserinfo;

public interface UserDao {
	//1.获得总记录数
	int getRowCount(@Param("userName")String userName);
	//2.获取分页信息的数据
	List<TbUserinfo> findObjects(@Param("startIndex") Integer startIndex,
								 @Param("pageSize") Integer pageSize,
								 @Param("userName") String userName);
	//修改状态信息
	void updateStateById(@Param("id")Integer id,@Param("state")Integer state);
	//根据id删除用户信息
	void delUserInfoById(Integer id);
	//批量删除用户信息
	int delUserInfoByIds(@Param("ids") Integer[] ids);
	//添加管理信息
	int saveUserInfo(@Param("user") TbUserinfo user);
	//根据Id查询对应的信息
	TbUserinfo findUserinfoById(@Param("id")Integer id);
	//修改管理员信息
	int updateUserinfo(@Param("user") TbUserinfo userinfo);
	//根据用户名和密码查询用户是否存在
	TbUserinfo findUserByNameAndPwd(@Param("userName") String username,
								    @Param("passWord") String password);
}
