package ssm.ctrl.system.userManage;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ssm.ctrl.common.BaseController;
import ssm.service.systemManage.system.UserManageService;
import ssm.utils.Const;
import ssm.utils.Page;
import ssm.utils.PageData;

import javax.annotation.Resource;
import javax.security.auth.Subject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/system/userManage")
public class UserManageCtrl extends BaseController {

	@Resource(name = "UserManageServiceImpl")
	private UserManageService userManageService;


	@RequestMapping("/toUserManage")
	public ModelAndView toUserManage (Page page) throws Exception {
		logger.info("UserManageCtrl toUserManage...");
		ModelAndView mv = new ModelAndView("/system/userManage/userIndex.html");
		List<PageData> userList = this.userManageService.selectUserList(page);
		mv.addObject("userList" ,userList);
		return mv;
	}

	/**跳转到添加用户界面*/
	@RequestMapping("/toAddUser")
	public ModelAndView toAddUser () throws Exception{
		logger.info("UserManageCtrl toAddUser...");
		ModelAndView mv = new ModelAndView("/system/userManage/addUser.html");
		return mv;
	}

	/**保存用户*/
	@RequestMapping("/saveUser")
	@ResponseBody
	public Map<String, Object> saveUser(){
		logger.info("UserManageCtrl saveUser...");
		Map<String,Object> map = new HashMap<String, Object>();
		PageData pd = this.getPageData();
		try {
			this.userManageService.saveUser(pd);
		} catch (Exception e) {
			map.put("error",e.getMessage());
			e.printStackTrace();
		}
		return map;
	}

}
