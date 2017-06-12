package ssm.service.systemManage.impl.system;

import org.springframework.stereotype.Service;
import ssm.service.common.impl.BaseServiceImpl;
import ssm.service.systemManage.system.UserManageService;
import ssm.state.AvailableState;
import ssm.utils.Page;
import ssm.utils.PageData;

import java.util.List;



/**
 * Created by Administrator on 2017/5/7.
 */
@Service("UserManageServiceImpl")
public class UserManageServiceImpl extends BaseServiceImpl implements UserManageService {


    public List<PageData> selectUserList(Page page) throws Exception {
        List<PageData> userList = (List<PageData>) this.daoSupport.findForList("UserManageMapper.selectUserlistPage",page);
        for (PageData userData:userList) {
            userData.put("available", AvailableState.codeToDesc(userData.get("available").toString()));
        }
        return userList;
    }
}