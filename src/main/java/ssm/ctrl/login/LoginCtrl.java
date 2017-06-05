package ssm.ctrl.login;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ssm.ctrl.common.BaseController;
import ssm.service.login.LoginService;
import ssm.utils.Const;
import ssm.utils.PageData;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;



@Controller
public class LoginCtrl extends BaseController {
	
	@Resource(name="LoginServiceImpl")
	private LoginService loginService;
	
	
	@RequestMapping("/loginOut.do")
	public String loginOut() {
		//shiro销毁登录
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		getRequest().getSession().removeAttribute(Const.SESSION_USER);
		getRequest().getSession().removeAttribute(Const.SESSION_USERNAME);
		return "/login.html";
	}
	
	//跳转到登录页面
	@RequestMapping(value="/login")
	public ModelAndView login (){
		ModelAndView mv = new ModelAndView("/login.html");
		return mv;
	}
	
	//登录
	@RequestMapping(value="/toLogin",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> toLogin (){
		logger.info("LoginCtrl toLogin...");
		Map<String, Object> map = new HashMap<String, Object>();
		PageData userData = this.getPageData();
		String userName = userData.getString("userName");
		String userPassword = userData.getString("password");
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName,userPassword); 
		try { 
			subject.login(token); 
			map.put("msg", "success");
		} catch (AuthenticationException e) { 
			map.put("message", "身份验证失败");
			logger.error(e.getMessage());
			return map;
		}
		return map;
	}
	
	@RequestMapping("/toIndex")
	public ModelAndView toIndex () {
		ModelAndView mv = new ModelAndView("/common/index.html");
		return mv;
	}
	
}
