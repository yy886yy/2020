package tz.cn.controller;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tz.cn.pojo.TbUserinfo;
import tz.cn.service.UserService;
import tz.cn.vo.PageData;
@Controller
@RequestMapping("/user")
public class UserController {
	private final Logger logger=Logger.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	//返回到index页面
	@RequestMapping("/returnIndex")
	public String index() {
		return "index";
	}
	//返回到user列表页面
	@RequestMapping("/returnList")
	public String returnList() {
		return "user/userList";
	}
	//跳转新增管理员页面
	@RequestMapping("/returnAddUser")
	public String returnAddUser() {
		return "user/addUserinfo";
	}
	//跳转到编辑页面
	@RequestMapping("/editUserInfo")
	public ModelAndView editUserInfo(Integer id) {
		TbUserinfo userinfo = userService.findUserinfoById(id);
		ModelAndView mv=new ModelAndView();
		mv.addObject("user",userinfo);
		mv.setViewName("user/editUserinfo");
		return mv;
	}
	//请求数据的方法
	@RequestMapping("/findUserList")
	@ResponseBody
	public PageData findUserList(@RequestParam("page") Integer currentPage,
								 @RequestParam("limit") Integer pageSize,
								 String userName){
		return userService.findObjects(currentPage, pageSize,userName);
	} 
	//修改状态
	@RequestMapping("/updateStateById")
	@ResponseBody
	public String updateStateById(Integer id,Boolean flag) {
		int state=flag==true?1:0;
		String result="";
		try {
			userService.updateStateById(id,state);
			result="1";
		}catch(Exception e) {
			result="0";
			e.printStackTrace();
		}
		return result;
	}
	//根据ID删除用户对象
	@RequestMapping("/delUserInfoById")
	@ResponseBody
	public String delUserInfoById(Integer id) {
		String result="";
		try {
			userService.delUserInfoById(id);
			result="1";
		}catch(Exception e) {
			result="0";
			e.printStackTrace();
		}
		return result;
	}
	//批量删除用户
	@RequestMapping("/delUserInfoByIds")
	@ResponseBody
	public String delUserInfoByIds(Integer[] ids) {
		String result="0";
		int count = userService.delUserInfoByIds(ids);
		if(count>0){
			result="1";
		}
		return result;
	}
	//添加用户
	@RequestMapping(value="/saveUserinfo",method=RequestMethod.POST)
	@ResponseBody
	public String saveUserinfo(@RequestBody TbUserinfo tbuserinfo) {
		String result="0";
		int count = userService.saveUserInfo(tbuserinfo);
		if(count>0) {
			result="1";
		}
		return result;
	}
	//修改管理员信息
	@RequestMapping("/updateUserinfo")
	@ResponseBody
	public String updateUserinfo(@RequestBody TbUserinfo tbuserinfo) {
		String result="0";
		int count = userService.updateUserinfo(tbuserinfo);
		if(count>0) {
			result="1";
		}
		return result;
	}
}
