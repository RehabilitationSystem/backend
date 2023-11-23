package com.example.commons;

import com.example.commons.annotation.UnInterception;
import com.example.commons.exceptiondeal.BusinessErrorException;
import com.example.commons.exceptiondeal.BusinessMsgEnum;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;
import java.util.Set;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    @Resource
    private RedisService redisService;


    public void VerifyToken(String string){

    }


    /**
     *
     * @param request 用户的请求
     * @param response
     * @param handler
     * @return  返回true才会继续执行，报错则取消当前请求
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        String requestURI = request.getRequestURI();

        // 通过方法，可以获取该方法上的自定义注解，然后通过注解来判断该方法是否要被拦截
        // @UnInterception 是我们自定义的注解
        UnInterception unInterception = method.getAnnotation(UnInterception.class);
        if (null != unInterception) {
            return true;
        }


        String token = request.getParameter("token");
        //String authorizationHeader = request.getHeader("Authorization");
        //String token = authorizationHeader.replace("Bearer ", "");


        if (null == token || "".equals(token)) {
            logger.info("用户未登录，没有权限执行……请登录");
            throw new BusinessErrorException(BusinessMsgEnum.TOKEN_NOT_USED);
        }

        //验证token是否合法
        JwtUtil.checkSign(token);


        //验证token是否重放或者过期
        Integer count = redisService.getCounter(token);
        if(count==null){
            throw new BusinessErrorException(BusinessMsgEnum.TOKEN_HAS_EXPIRED);
        }

        redisService.incrementCounter(token);


        //验证是否token盗用
//        Integer counter =  Integer.parseInt(request.getParameter("counter"));
        Integer counter =2;
        String sessionId = redisService.getSessionId(token);
        HttpSession session = request.getSession();
        Set<String> urlSet= (Set<String>)session.getAttribute("permissions");
//        if(!sessionId.equals(session.getId())){
//            throw new BusinessErrorException(BusinessMsgEnum.TOKEN_STOLEN);
//        }
//        if(counter!=redisService.getCounter(token)){
//            throw new BusinessErrorException(BusinessMsgEnum.TOKEN_SAME_COUNTER);
//        }

        if(!checkPermissions(urlSet,requestURI)){
            throw new BusinessErrorException(BusinessMsgEnum.HAS_NOT_PERMISSIONS);
        }

        return true;
    }

    private Boolean checkPermissions(Set<String> urlSet,String requestURI){
        for (String allowedUrl : urlSet) {
            if (requestURI.startsWith(allowedUrl)) {
                // 如果请求路径以允许的URL开头，则表示该请求路径被允许
                return true;
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("执行完方法之后进执行(Controller方法调用之后)，但是此时还没进行视图渲染");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("整个请求都处理完咯，DispatcherServlet也渲染了对应的视图咯，此时我可以做一些清理的工作了");
    }


}
