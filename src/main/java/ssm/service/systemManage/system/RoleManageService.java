package ssm.service.systemManage.system;

import ssm.utils.Page;
import ssm.utils.PageData;

import java.util.List;

/**
 * Created by dxgong on 2017/7/4.
 */
public interface RoleManageService {

    /**查询全部角色*/
    public List<PageData> findRole(Page page) throws Exception;
}
