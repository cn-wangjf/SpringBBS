package cn.wjf.bbs.common;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: wangjf
 * Date: 14-11-14
 * Time: 下午4:30
 */
public class ForumHandlerExceptionResolver extends SimpleMappingExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        request.setAttribute("ex", ex);
        ex.printStackTrace();
        return super.doResolveException(request, response, handler, ex);
    }
}
