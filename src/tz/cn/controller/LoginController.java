package tz.cn.controller;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tz.cn.pojo.TbUserinfo;
import tz.cn.service.UserService;
@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	@RequestMapping("/login")
	@ResponseBody
	public String login(String username,String password,String code,HttpSession session) {
		String result="1";
		String session_code =(String)session.getAttribute("CODE");
		TbUserinfo user= userService.findUserByNameAndPwd(username,password);
		if(!code.equalsIgnoreCase(session_code)){
			result="3";
		}else if(StringUtils.isEmpty(user)) {
			result="2";
		}else if(user.getState()==0) {
			result="4";
		}
		//将用户保存到session中
		session.setAttribute("user",user);
		return result;
	}
	@RequestMapping("/logOut")
	public String logOut(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/login.jsp";
	}
}
