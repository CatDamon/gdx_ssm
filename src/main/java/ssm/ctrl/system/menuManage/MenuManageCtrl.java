package ssm.ctrl.system.menuManage;

import org.omg.CORBA.OBJECT_NOT_EXIST;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ssm.ctrl.common.BaseController;
import ssm.service.systemManage.system.MenuManageService;
import ssm.state.IsHeaderState;
import ssm.utils.Page;
import ssm.utils.PageData;

import javax.annotation.Resource;
import javax.jws.WebParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/system/MenuManageCtrl")
public class MenuManageCtrl extends BaseController {

	@Resource(name = "MenuManageServiceImpl")
	private MenuManageService menuManageService;


	/**跳转到菜单管理界面*/
	@RequestMapping("/toMenuIndex")
	public ModelAndView toMenuIndex(Page page) throws Exception {
		logger.info("MenuManageCtrl toMenuIndex...");
		ModelAndView mv = new ModelAndView("/system/menuManage/menuIndex.html");

		mv.addObject("menuList" ,(List<PageData>)this.menuManageService.selectMenu(page));
		return mv;
	}

	/**保存菜单权限*/
	@RequestMapping("/saveMenu")
	@ResponseBody
	public Map<String, Object> saveMenu(){
		logger.info("MenuManageCtrl saveMenu...");
		Map<String, Object> map = new HashMap<String, Object>();
		PageData pd = this.getPageData();
		try {
			this.menuManageService.saveMenu(pd);
		} catch (Exception e) {
			map.put("error",e.getMessage());
			e.printStackTrace();
		}
		return map;
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
