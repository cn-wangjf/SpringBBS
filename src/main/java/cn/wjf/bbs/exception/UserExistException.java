package cn.wjf.bbs.exception;

/**
 * User: wangjf
 * Date: 14-11-14
 * Time: 下午12:05
 */
public class UserExistException extends Exception {

    public UserExistException(String errorMsg) {
        super(errorMsg);
    }
}
