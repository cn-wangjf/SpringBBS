package cn.wjf.bbs.controller;

import cn.wjf.bbs.common.CommonConstant;
import cn.wjf.bbs.common.Page;
import cn.wjf.bbs.domain.Board;
import cn.wjf.bbs.domain.Post;
import cn.wjf.bbs.domain.Topic;
import cn.wjf.bbs.domain.User;
import cn.wjf.bbs.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * User: wangjf
 * Date: 14-11-14
 * Time: 下午3:13
 * 处理论坛普通操作功能的请求，包括：显示论坛版块列表、显示论坛版块主题列表、
 * 主题帖、回复帖子、删除帖子、设置精华帖子等操作。
 */
@Controller
public class BoardManageController extends BaseController {
    @Autowired
    private ForumService forumService;

    /**
     * 列出论坛模块下的主题帖子
     *
     * @param boardId
     * @param pageNo
     * @return
     */
    @RequestMapping(value = "/board/listBoardTopics-{boardId}", method = RequestMethod.GET)
    public ModelAndView listBoardTopics(@PathVariable Integer boardId,@RequestParam(value = "pageNo", required = false) Integer pageNo) {
        ModelAndView view =new ModelAndView();
        Board board = forumService.getBoardById(boardId);
        pageNo = pageNo==null?1:pageNo;
        Page pagedTopic = forumService.getPagedTopics(boardId, pageNo, CommonConstant.PAGE_SIZE);
        view.addObject("board", board);
        view.addObject("pagedTopic", pagedTopic);
        view.setViewName("/listBoardTopics");
        return view;
    }

    /**
     * 添加主题帖页面
     *
     * @param boardId
     * @return
     */
    @RequestMapping(value = "/board/addTopicPage-{boardId}", method = RequestMethod.GET)
    public ModelAndView addTopicPage(@PathVariable Integer boardId) {
        ModelAndView view =new ModelAndView();
        view.addObject("boardId", boardId);
        view.setViewName("/addTopic");
        return view;
    }

    /**
     * 列出主题的所有帖子
     *
     * @param topicId
     * @param pageNo
     * @return
     */
    @RequestMapping(value = "/board/listTopicPosts-{topicId}", method = RequestMethod.GET)
    public ModelAndView listTopicPosts(@PathVariable Integer topicId,@RequestParam(value = "pageNo", required = false) Integer pageNo) {
        ModelAndView view =new ModelAndView();
        Topic topic = forumService.getTopicByTopicId(topicId);
        pageNo = pageNo==null?1:pageNo;
        Page pagedPost = forumService.getPagedPosts(topicId, pageNo,
                CommonConstant.PAGE_SIZE);
        // 为回复帖子表单准备数据
        view.addObject("topic", topic);
        view.addObject("pagedPost", pagedPost);
        view.setViewName("/listTopicPosts");
        return view;
    }

    /**
     * 回复主题
     *
     * @param request
     * @param post
     * @return
     */
    @RequestMapping(value = "/board/addPost")
    public String addPost(HttpServletRequest request, Post post) {
        post.setCreateTime(new Date());
        post.setUser(getSessionUser(request));
        forumService.addPost(post);
        String targetUrl = "/board/listTopicPosts-"
                + post.getTopic().getTopicId() + ".html";
        return "redirect:"+targetUrl;
    }

    /**
     * 删除版块
     *
     * @param boardIds
     * @param boardIds
     */
    @RequestMapping(value = "/board/removeBoard", method = RequestMethod.GET)
    public String removeBoard(@RequestParam("boardIds") String boardIds) {
        String[] arrIds = boardIds.split(",");
        for (int i = 0; i < arrIds.length; i++) {
            forumService.removeBoard(new Integer(arrIds[i]));
        }
        String targetUrl = "/index.html";
        return "redirect:"+targetUrl;
    }

    /**
     * 删除主题
     *
     * @param topicIds
     * @param boardId
     */
    @RequestMapping(value = "/board/removeTopic", method = RequestMethod.GET)
    public String removeTopic(@RequestParam("topicIds") String topicIds,@RequestParam("boardId") String boardId) {
        String[] arrIds = topicIds.split(",");
        for (int i = 0; i < arrIds.length; i++) {
            forumService.removeTopic(new Integer(arrIds[i]));
        }
        String targetUrl = "/board/listBoardTopics-" + boardId + ".html";
        return "redirect:"+targetUrl;
    }

    /**
     * 设置精华帖
     * @param topicIds
     * @param boardId
     */
    @RequestMapping(value = "/board/makeDigestTopic", method = RequestMethod.GET)
    public String makeDigestTopic(@RequestParam("topicIds") String topicIds,@RequestParam("boardId") String boardId) {
        String[] arrIds = topicIds.split(",");
        for (int i = 0; i < arrIds.length; i++) {
            forumService.makeDigestTopic(new Integer(arrIds[i]));
        }
        String targetUrl = "/board/listBoardTopics-" + boardId + ".html";
        return "redirect:"+targetUrl;
    }

    /**
     * 添加一个主题帖
     *
     * @param request
     * @param topic
     * @return
     */
    @RequestMapping(value = "/board/addTopic", method = RequestMethod.POST)
    public String addTopic(HttpServletRequest request,Topic topic) {
        User user = getSessionUser(request);
        topic.setUser(user);
        Date now = new Date();
        topic.setCreateTime(now);
        topic.setLastPost(now);
        forumService.addTopic(topic);
        String targetUrl = "/board/listBoardTopics-" + topic.getBoardId()
                + ".html";
        return "redirect:"+targetUrl;
    }
}