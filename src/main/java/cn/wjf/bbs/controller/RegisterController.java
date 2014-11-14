package cn.wjf.bbs.controller;

import cn.wjf.bbs.domain.User;
import cn.wjf.bbs.exception.UserExistException;
import cn.wjf.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * User: wangjf
 * Date: 14-11-14
 * Time: 下午4:04
 */
@Controller
public class RegisterController extends BaseController {

    @Autowired
    private UserService userService;



    /**
     * 用户登录
     * @param request
     * @param user
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(HttpServletRequest request,User user){
        ModelAndView view = new ModelAndView();
        view.setViewName("/success");
        try {
            userService.register(user);
        } catch (UserExistException e) {
            view.addObject("errorMsg", "用户名已经存在，请选择其它的名字。");
            view.setViewName("forward:/register.jsp");
        }
        setSessionUser(request,user);
        return view;
    }
}
