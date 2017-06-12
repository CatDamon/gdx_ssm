package ssm.service.systemManage.impl.system;

import org.springframework.stereotype.Service;
import ssm.service.common.impl.BaseServiceImpl;
import ssm.service.systemManage.system.MenuManageService;
import ssm.utils.Page;
import ssm.utils.PageData;

import java.util.List;


/**
 * Created by dxgong on 2017/5/7.
 */
@Service("MenuManageServiceImpl")
public class MenuManageServiceImpl extends BaseServiceImpl implements MenuManageService {


    @Override
    public Object getMenuJson(PageData pageData) {
        logger.info("MenuManageServiceImpl getMenuJson...");
        StringBuffer menuStr = new StringBuffer();
        //查询该用户所拥有菜单
        menuStr.append("[{");
        menuStr.append("id");

        menuStr.append("}]");
        return null;
    }



    @Override
    public List<PageData> selectMenu(Page page) throws Exception {

        return (List<PageData>) this.daoSupport.findForList("MenuManageMapper.selectMenulistPage",page);
    }
}
