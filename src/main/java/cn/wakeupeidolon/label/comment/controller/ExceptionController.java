package cn.wakeupeidolon.label.comment.controller;

import cn.wakeupeidolon.label.comment.domain.Result;
import cn.wakeupeidolon.label.comment.domain.enums.ErrorCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wang Yu
 */
@RestControllerAdvice
public class ExceptionController {
    
    private static final Logger LOG = LoggerFactory.getLogger(ExceptionController.class);
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Void> bindException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        List<String> errMsg = new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errMsg.add(fieldError.getDefaultMessage());
        }
        return Result.error(StringUtils.join(',', errMsg));
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Void> fatherException(Exception e, HandlerMethod method){
        String name = method.getMethod().getName();
        LOG.error("方法: " + name + ", 异常: " + e.getMessage() + " 行");
        return Result.error(ErrorCode.UNKNOWN_ERROR);
    }
    
}
