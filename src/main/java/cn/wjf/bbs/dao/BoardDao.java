package cn.wjf.bbs.dao;

import cn.wjf.bbs.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.Iterator;

/**
 * User: wangjf
 * Date: 14-11-13
 * Time: 上午10:31
 */
@Repository
public class BoardDao extends BaseDao<Board> {

    protected final String GET_BOARD_NUM = "select count(b.boardId) from Board b";

    /**
     * 获取板块数
     * @return
     */
    public long getBoardNum() {
        Iterator iter = getHibernateTemplate().iterate(GET_BOARD_NUM);
        return ((Long)iter.next());
    }
}
