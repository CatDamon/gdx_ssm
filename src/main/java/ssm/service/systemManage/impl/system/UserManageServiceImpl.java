package ssm.service.systemManage.impl.system;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ssm.exception.SystemServiceException;
import ssm.service.common.impl.BaseServiceImpl;
import ssm.service.systemManage.system.UserManageService;
import ssm.state.AvailableState;
import ssm.utils.CodecAndCrypUtil;
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

    /**
     * 添加用户
     */
    @Override
    public void saveUser(PageData pageData) throws Exception {
        logger.info("UserManageServiceImpl saveUser...");
        //判断该用户名是否已经存在，存在则添加失败
        List<PageData> ishasAccount = (List<PageData>) this.daoSupport.findForList("UserManageMapper.isHasSaveAccount",pageData);
        if(ishasAccount.size()>0){ //说明该用户名已经存在
            throw new SystemServiceException("该用户名已经存在,请重新输入用户名!");
        }
        //密码DES加密
        pageData.put("userid",get32UUID());
        pageData.put("available",AvailableState.WJH.getType_code());
        if(StringUtils.isNotBlank(pageData.getString("password"))){
            pageData.put("password", CodecAndCrypUtil.MD5(pageData.getString("password")));
        }
        this.daoSupport.save("UserManageMapper.saveUser",pageData);
    }

    /**
     * 激活用户
     *
     * @param pageData
     */
    @Override
    public void activativeAccount(PageData pageData) throws Exception {
        logger.info("UserManageServiceImpl pageData...");
        if(StringUtils.isNoneBlank(pageData.getString("userid"))){
            if(AvailableState.WJH.getShort_desc().equals(pageData.getString("availableCode"))){
                pageData.put("available",AvailableState.YJH.getType_code());
            }else{
                pageData.put("available",AvailableState.WJH.getType_code());
            }
            this.daoSupport.update("UserManageMapper.activativeAccount",pageData);
        }else{
            throw new SystemServiceException("用户id不能为空!");
        }
    }

    /**
     * 修改用户
     *
     * @param pageData
     */
    @Override
    public void editUser(PageData pageData) throws Exception {
        logger.info("UserManageServiceImpl editUser...");
        if(StringUtils.isNotBlank(pageData.getString("password"))){
            pageData.put("password", CodecAndCrypUtil.MD5(pageData.getString("password")));
        }
        this.daoSupport.update("UserManageMapper.editUser",pageData);
    }

    /**
     * 删除用户
     *
     * @param pageData
     */
    @Override
    public void delUser(PageData pageData) throws Exception {
        logger.info("UserManageServiceImpl delUser...");
        this.daoSupport.delete("UserManageMapper.delUser",pageData);

    }

    /**
     * 查询所有角色
     */
    @Override
    public List<PageData> findAllRole() throws Exception {
        logger.info("UserManageServiceImpl findAllRole...");
        return (List<PageData>) this.daoSupport.findForList("UserManageMapper.findAllRole");
    }


}
