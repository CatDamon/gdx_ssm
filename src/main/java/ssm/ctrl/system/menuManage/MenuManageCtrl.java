package ssm.ctrl.system.menuManage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ssm.ctrl.common.BaseController;
import ssm.service.systemManage.system.MenuManageService;
import ssm.utils.Page;
import ssm.utils.PageData;

import javax.annotation.Resource;
import javax.jws.WebParam;
import java.util.List;


@Controller
@RequestMapping("/system/MenuManageCtrl")
public class MenuManageCtrl extends BaseController {

	@Resource(name = "MenuManageServiceImpl")
	private MenuManageService menuManageService;


	/**跳转到菜单管理界面*/
	@RequestMapping("/toMenuIndex")
	public ModelAndView toMenuIndex(Page page) throws Exception {
		logger.info("MenuManageCtrl toMenuIndex...");
		ModelAndView mv = new ModelAndView("/system/menuManage/menuIndex2.html");

		mv.addObject("menuList" ,(List<PageData>)this.menuManageService.selectMenu(page));
		return mv;
	}

	/**跳转到添加菜单页*/
	@RequestMapping("/toAddMenuPage")
	public ModelAndView toAddMenuPage()throws Exception{
		logger.info("MenuManageCtrl toAddMenuPage...");
		ModelAndView mv = new ModelAndView("/system/menuManage/addMenu.html");

		return mv;
	}


	/**动态生成用户菜单*/
	@RequestMapping("/getMenuJson")
	@ResponseBody
	public Object getMenuJson()  {
		logger.info("MenuManageCtrl getMenuJson...");
		PageData pd = new PageData();
		pd.put("userid" ,this.getUser().getUserid());
		Object obj = null;
		try {
			obj = this.menuManageService.getMenuJson(pd);

		} catch (Exception e) {
			e.printStackTrace();
			pd.put("errorInfo",e.getMessage());
		}
		System.out.print(obj);
		return null;
	}




}
