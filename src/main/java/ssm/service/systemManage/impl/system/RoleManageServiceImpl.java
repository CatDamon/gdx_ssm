package ssm.service.systemManage.impl.system;

import org.springframework.stereotype.Service;
import ssm.service.common.impl.BaseServiceImpl;
import ssm.service.systemManage.system.RoleManageService;
import ssm.utils.Page;
import ssm.utils.PageData;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dxgong on 2017/7/4.
 */
@Service("RoleManageServiceImpl")
public class RoleManageServiceImpl extends BaseServiceImpl implements RoleManageService{


    /**
     * 查询全部角色
     *
     * @param page
     */
    @Override
    public List<PageData> findRole(Page page) throws Exception {
        logger.info("RoleManageServiceImpl findRole...");

        return null;
    }
}
