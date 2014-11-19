import cn.wjf.bbs.common.EncryptionUtil;
import cn.wjf.bbs.domain.Board;

/**
 * User: wangjf
 * Date: 14-11-19
 * Time: 下午5:17
 */
public class MainTest {

    public static void main(String[] args) {
        System.out.println(EncryptionUtil.getMd5(123123));
        System.exit(0);
    }
}
