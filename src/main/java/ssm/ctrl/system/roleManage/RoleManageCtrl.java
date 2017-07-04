package ssm.ctrl.system.roleManage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ssm.ctrl.common.BaseController;
import ssm.service.systemManage.system.RoleManageService;
import ssm.utils.Page;

import javax.annotation.Resource;
import java.awt.event.MouseEvent;

/**
 * Created by dxgong on 2017/7/4.
 */
@RequestMapping("/system/RoleManageCtrl")
@Controller
public class RoleManageCtrl extends BaseController {

    @Resource(name="RoleManageServiceImpl")
    private RoleManageService roleManageService;

    /**跳转到角色列表*/
    @RequestMapping("/toRoleIndex")
    public ModelAndView toRoleIndex (Page page){
        logger.info("RoleManageCtrl toRoleIndex...");
        ModelAndView mv = new ModelAndView("");
        return mv;
    }

}
