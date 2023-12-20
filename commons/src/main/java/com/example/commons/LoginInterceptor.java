package com.example.commons;

import com.example.commons.annotation.UnInterception;
import com.example.commons.config.Constants;
import com.example.commons.exceptiondeal.BusinessErrorException;
import com.example.commons.exceptiondeal.BusinessMsgEnum;
import com.example.commons.service.JwtUtil;
import com.example.commons.service.RedisService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;
import java.net.http.HttpRequest;
import java.util.Set;
import java.util.concurrent.Callable;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    @Resource
    private RedisService redisService;

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
        // 通过方法，可以获取该方法上的自定义注解，然后通过注解来判断该方法是否要被拦截
        UnInterception unInterception = method.getAnnotation(UnInterception.class);
        if (null != unInterception) {
            return true;
        }

        //获取token
        String token = getParam(request,"token",BusinessMsgEnum.TOKEN_MISSING);

        //检查token本身是否合法
        HttpSession session = request.getSession();
        checkToken(token,session);

        //序列计数
        Long counter =  Long.parseLong(getParam2(request,"counter",BusinessMsgEnum.COUNTER_MISSING));
        checkCounter(counter,token);

        //权限
        String requestURI = request.getRequestURI();
        Set<String> urlSet= (Set<String>)session.getAttribute("permissions");
        checkUrl(urlSet,requestURI);
        return true;
    }

    /**
     * 从请求中获取参数
     * @param request 请求对象
     * @param key 参数对应的键
     * @return 获取的参数
     */
    @SneakyThrows
    private String getParam(HttpServletRequest request,String key,BusinessMsgEnum businessMsgEnum){
        String token;
        if((token=request.getParameter(key))==null){
            if((token = request.getHeader(key))==null){
                try {
                    String authorizationHeader = request.getHeader("Authorization");
                    token = authorizationHeader.replace("Bearer ", "");
                } catch (Exception e) {
                    throw new BusinessErrorException(businessMsgEnum);
                }
            }
        }
        return token;
    }

    /**
     * 从请求中获取参数,无认证头格式
     * @param request 请求对象
     * @param key 参数对应的键
     * @return 获取的参数
     */
    @SneakyThrows
    private String getParam2(HttpServletRequest request,String key,BusinessMsgEnum businessMsgEnum){
        String token;
        if((token=request.getParameter(key))==null){
            if((token = request.getHeader(key))==null){
                    throw new BusinessErrorException(businessMsgEnum);
            }
        }
        return token;
    }

    /**
     * 检验用户是否有uri权限
     * @param urlSet 允许uri权限集合
     * @param requestURI 请求访问的uri
     */
    private void checkUrl(Set<String> urlSet,String requestURI){
        try {
            if(!checkPermissions(urlSet,requestURI)){
                throw new BusinessErrorException(BusinessMsgEnum.HAS_NOT_PERMISSIONS);
            }
        } catch (NullPointerException e) {
            logger.info("uriSet没有进行初始化!");
            throw new BusinessErrorException(BusinessMsgEnum.URI_SET_NULL);
        }
    }

    /**
     * 检查token本身的内容,token是否携带,是否合法,sessionId是否对的上.
     * @param token
     * @param session
     */
    private void checkToken(String token,HttpSession session){
        if (null == token || "".equals(token)) {
            logger.info("用户未登录，没有权限执行……请登录");
            throw new BusinessErrorException(BusinessMsgEnum.TOKEN_NOT_USED);
        }
        //验证token是否合法
        JwtUtil.checkSign(token);
//        String sessionId = redisService.getSessionId(token);
//        if(!sessionId.equals(session.getId())){
//            throw new BusinessErrorException(BusinessMsgEnum.TOKEN_STOLEN);
//        }

    }

    /**
     * 检查计数器部分,单独设置是为了方便进行同步处理.
     * @param counter 请求提供的计数器的值
     * @param token 用户验证令牌
     */
    private void checkCounter(Long counter,String token){
        redisService.callWithLock(Constants.COUNTER_LOCK_KEY+token, new Callable() {
            @Override
            public Object call(){
                //        用户初始化个人信息
                //序列计数器是否还缓存在redis里
                Long count = redisService.getCounter(token);
                if(count==null){
                    throw new BusinessErrorException(BusinessMsgEnum.TOKEN_HAS_EXPIRED);
                }
                //序列计数器是否符和预期
                if(!count.equals(counter)){
                    throw new BusinessErrorException(BusinessMsgEnum.TOKEN_SAME_COUNTER);
                }
                //原子增加下一次期待的计数器的值

                redisService.incrementCounter(token);
                return null;
            }
        });
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


}
