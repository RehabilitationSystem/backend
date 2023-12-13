package com.example.commons.exceptiondeal;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.commons.config.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    // 打印log
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    // ……
    /**
     * 缺少请求参数异常
     * @param ex HttpMessageNotReadableException
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public JsonResult handleHttpMessageNotReadableException(
            MissingServletRequestParameterException ex) {
        logger.error("缺少请求参数，{}", ex.getMessage());
        return new JsonResult("400", "缺少必要的请求参数");
    }

    /**
     * 空指针异常
     * @param ex NullPointerException
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult handleTypeMismatchException(NullPointerException ex) {
        logger.error("空指针异常，{}", ex.getMessage());
        return new JsonResult("500", "空指针异常了");
    }





    /**
     * 拦截业务异常，返回业务异常信息
     * @param ex
     * @return
     */
    @ExceptionHandler(BusinessErrorException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public JsonResult handleBusinessError(BusinessErrorException ex) {
        String code = ex.getCode();
        String message = ex.getMessage();
        logger.error("业务异常："+ message);
        return new JsonResult(code, message);
    }

    /**
     *
     * @param ex 通常发生在处理HTTP请求时，当请求的body不能被正确的解析或转换为期望的Java对象时，就会抛出这个异常。
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult handleMessageNotReadableError(HttpMessageNotReadableException ex) {
        String message = ex.getMessage();
        logger.error("body不能被正确的解析异常：", ex);
        return new JsonResult("400", message);
    }

    /**
     * 数据校验异常处理 - MethodArgumentNotValidException（继承BindException）
     * controller中方法上配置数据校验（参数为javabean，校验规则配置在bean中），校验不通过会抛出异常MethodArgumentNotValidException
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object handlerViolationException(MethodArgumentNotValidException e) {
        HashMap<String, String> errMap = new HashMap<>(e.getFieldErrors().size());
        for (FieldError error : e.getFieldErrors()) {
            //示例： "tagName": "不能为空"
            errMap.put(error.getField(),error.getDefaultMessage());
            // 记录日志
            logger.error("参数异常",error.getDefaultMessage());
        }
        return new JsonResult<HashMap<String,String>>(errMap,"400",e.getMessage());
    }

    /**
     * token认证失败异常处理 - JWTVerificationException
     * controller中方法上配置token校验，校验不通过会抛出JWTVerificationException
     */
    @ExceptionHandler(JWTVerificationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object handlerViolationException(JWTVerificationException ex) {
        String message = ex.getMessage();
        logger.error("token认证异常异常：", ex);
        return new JsonResult("403", message);
    }


    /**
     * token认证失败异常处理 - JWTVerificationException
     * controller中方法上配置token校验，校验不通过会抛出JWTVerificationException
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object handlerViolationException(HttpRequestMethodNotSupportedException ex) {
        String message = ex.getMessage();
        logger.error("请求方式错误异常：", ex);
        return new JsonResult("405", message);
    }

    /**
     *  redis服务器连接异常
     * @param ex
     * @return
     */
    @ExceptionHandler(RedisConnectionFailureException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult handleUnexpectedServer(RedisConnectionFailureException ex) {
        logger.error("redis服务连接异常异常：", ex);
        return new JsonResult("500", "系统发生异常，请联系管理员");
    }


    /**
     *系统异常 预期以外异常
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult handleUnexpectedServer(Exception ex) {
        logger.error("系统异常：", ex);
        return new JsonResult("500", "系统发生异常，请联系管理员");
    }




}
