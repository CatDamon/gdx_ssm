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
        if(IsMenuOrPointState.YMQXD.getType_code().equals(pageData.getString("ismenuorpoint"))){  //如果是页面权限点，则isheader的值改成非目录
            pageData.put("isheader",IsHeaderState.FGML.getType_code());
        }
        //判断该保存权限类别
        // 当且仅当ismenuorpoint属于菜单权限,并且isheader属于根目录时候,上级菜单不用做校验
        if(!(IsMenuOrPointState.CDQX.getType_code().equals(pageData.getString("ismenuorpoint")) && IsHeaderState.GML.getType_code().equals(pageData.getString("isheader")))){
            if(!StringUtil.isNotBlank(pageData.getString("parentname"))){  //非菜单根目录都需要选择上级菜单
                throw new SystemServiceException("该目录不是根目录,请在右侧点击选择相应的上级菜单");
            }else{

                this.daoSupport.save("MenuManageMapper.saveMenu",pageData);
            }
        }else{
            this.daoSupport.save("MenuManageMapper.saveMenu",pageData);
        }

    }

    /**
     * 返回权限目录树ztree
     *
     */
    @Override
    public String returnZtreeData() throws Exception {
        logger.info("MenuManageServiceImpl returnZtreeData...");
        //查询菜单权限的根目录
        PageData pd = new PageData();
        pd.put("isheader",IsHeaderState.GML.getType_code());
        pd.put("ismenuorpoint",IsMenuOrPointState.CDQX.getType_code());
        List<PageData> rootList = (List<PageData>) this.daoSupport.findForList("MenuManageMapper.returnZtreeMenuRootData",pd);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i =0; i<rootList.size(); i++){
            sb.append("{");
            sb.append("id:\""+ rootList.get(i).getString("per_id")+"\",");
            sb.append("name:\""+ rootList.get(i).getString("per_name")+"\",");
            sb.append("open:"+ false+",");
            sb.append("isParent:"+ true);
            sb.append("}");
            if(i != (rootList.size()-1)){//判断不是最后一个,后面加,号
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * 根据ID查询该权限下所有目录
     *
     * @param pageData
     */
    @Override
    public String getSonMenu(PageData pageData) throws Exception {
        logger.info("MenuManageServiceImpl getSonMenu...");
        StringBuilder sb = new StringBuilder(); //封装ztree目录数据
        if(StringUtil.isNotBlank(pageData.getString("id"))){
            List<PageData> list = (List<PageData>) this.daoSupport.findForList("MenuManageMapper.findSonMenu",pageData);
            if(list.size() == 0){
                throw new SystemServiceException("undefined");
            }
            sb.append("[");
            for(int i=0; i<list.size(); i++){
                PageData pd = list.get(i);
                sb.append("{");
                sb.append("id:\""+ pd.getString("per_id")+"\",");
                sb.append("name:\""+ pd.getString("per_name")+"\",");
                if(IsHeaderState.GML.getType_code().equals(pd.get("isheader").toString())){ //判断是否是父目录
                    sb.append("isParent:"+ true +",");
                }
                sb.append("open:"+ false);
                sb.append("}");
                if(i != (list.size()-1)){//判断不是最后一个,后面加,号
                    sb.append(",");
                }
            }
            sb.append("]");
        }else{
            throw new SystemServiceException("权限id不能为空!");
        }
        return sb.toString();
    }


}
