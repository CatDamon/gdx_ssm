package ssm.service.systemManage.system;

import ssm.utils.PageData;

/**
 * Created by dxgong on 2017/6/5.
 */
public interface MenuManageService {

    /**根据登录用户动态生成左侧菜单*/
    public Object getMenuJson(PageData pageData);
}
