package ssm.service.login.impl;

import org.springframework.stereotype.Service;
import ssm.service.common.impl.BaseServiceImpl;
import ssm.service.login.LoginService;
import ssm.utils.PageData;

import java.util.List;


@Service("LoginServiceImpl")
public class LoginServiceImpl extends BaseServiceImpl implements LoginService {

	public List<PageData> select() throws Exception {
		return (List<PageData>) this.findForList("loginMapper.select");
	}
	
	
}
