package ssm.service.systemManage.system;




import ssm.utils.Page;
import ssm.utils.PageData;

import java.util.List;

/**
 *
 * Created by dxgong on 2017/5/7.
 *
 */
public interface UserManageService {

    public List<PageData> selectUserList(Page page) throws Exception;
}



