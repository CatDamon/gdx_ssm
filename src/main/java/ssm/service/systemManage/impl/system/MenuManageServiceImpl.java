package ssm.service.systemManage.impl.system;

import org.eclipse.jetty.util.StringUtil;
import org.springframework.stereotype.Service;
import ssm.exception.SystemServiceException;
import ssm.service.common.impl.BaseServiceImpl;
import ssm.service.systemManage.system.MenuManageService;
import ssm.state.IsHeaderState;
import ssm.state.IsMenuOrPointState;
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

    /**
     * 保存菜单
     *
     * @param pageData
     */
    @Override
    public void saveMenu(PageData pageData) throws Exception {
        logger.info("MenuManageServiceImpl saveMenu...");
        pageData.put("per_id",get32UUID()); //设置主键
        //判断该保存权限类别
        // 当且仅当ismenuorpoint属于菜单权限,并且isheader属于根目录时候,上级菜单不用做校验
        if(!(IsMenuOrPointState.CDQX.getType_code().equals(pageData.getString("ismenuorpoint")) && IsHeaderState.GML.getType_code().equals(pageData.getString("isheader")))){
            if(!StringUtil.isNotBlank(pageData.getString("perentname"))){  //非菜单根目录都需要选择上级菜单
                throw new SystemServiceException("该目录不是根目录,请在右侧点击选择相应的上级菜单");
            }
        }else{
            this.daoSupport.save("MenuManageMapper.saveMenu",pageData);
        }

    }


}
