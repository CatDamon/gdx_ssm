package ssm.service.login;



import ssm.utils.PageData;

import java.util.List;


public interface LoginService {
	
	//查询
	public List<PageData> select()  throws Exception;
}
