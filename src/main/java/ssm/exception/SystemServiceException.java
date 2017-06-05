package ssm.exception;

/**
 * Created by dxgong on 2017/6/5.
 */
public class SystemServiceException extends  RuntimeException{

    public SystemServiceException() {
    }

    public SystemServiceException(String msg){
        super(msg);
    }
}
