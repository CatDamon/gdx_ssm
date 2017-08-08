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
        return (List<PageData>) this.daoSupport.findForList("RoleManageMapper.selectRolelistPage",page);
    }

    /**
     * 保存角色
     *
     * @param pageData
     */
    @Override
    public void saveRole(PageData pageData) throws Exception {
        logger.info("RoleManageServiceImpl saveRole...");
        pageData.put("roleid",get32UUID());
        this.daoSupport.save("RoleManageMapper.saveRole",pageData);
    }

    /**
     * 删除角色
     *
     * @param pageData
     */
    @Override
    public void delRole(PageData pageData) throws Exception {
        logger.info("RoleManageServiceImpl delRole...");
        this.daoSupport.delete("RoleManageMapper.delRole",pageData);
    }

    /**
     * 修改角色
     *
     * @param pageData
     */
    @Override
    public void editRole(PageData pageData) throws Exception {
        logger.info("RoleManageServiceImpl editRole...");
        this.daoSupport.update("RoleManageMapper.editRole",pageData);
    }
}
