package ssm.ctrl.system.menuManage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.ctrl.common.BaseController;
import ssm.service.systemManage.system.MenuManageService;

import javax.annotation.Resource;


@Controller
@RequestMapping("/system/MenuManageCtrl")
public class MenuManageCtrl extends BaseController {

	@Resource(name = "MenuManageServiceImpl")
	private MenuManageService menuManageService;

	@RequestMapping("/getMenuJson")
	@ResponseBody
	public Object getMenuJson(){

		return null;
	}



}
