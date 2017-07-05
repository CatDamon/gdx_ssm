package ssm.ctrl.system.roleManage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ssm.ctrl.common.BaseController;
import ssm.service.systemManage.system.RoleManageService;
import ssm.utils.Page;
import ssm.utils.PageData;

import javax.annotation.Resource;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ModelAndView toRoleIndex (Page page) throws Exception{
        logger.info("RoleManageCtrl toRoleIndex...");
        ModelAndView mv = new ModelAndView("/system/roleManage/roleIndex.html");
        PageData pd = this.getPageData();
        this.setConditionForQuery(pd,mv,page);
        List<PageData> roleList = this.roleManageService.findRole(page);
        mv.addObject("roleList",roleList);
        return mv;
    }

    /**跳转到添加角色页面*/
    @RequestMapping("/toAddRole")
    public ModelAndView toAddRole () throws Exception{
        logger.info("UserManageCtrl toAddRole...");
        ModelAndView mv = new ModelAndView("/system/roleManage/addRole.html");
        return mv;
    }

    /**保存角色*/
    @RequestMapping("/saveRole")
    @ResponseBody
    public Map<String, Object> saveRole(){
        logger.info("UserManageCtrl saveRole...");
        Map<String,Object> map = new HashMap<String, Object>();
        PageData pd = this.getPageData();
        try {
            this.roleManageService.saveRole(pd);
        } catch (Exception e) {
            map.put("error","添加失败!");
            e.printStackTrace();
        }
        return map;
    }

}
