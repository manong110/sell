package com.inspur.cn.common.exceptions;

import com.inspur.cn.common.dto.ResultDto;
import com.inspur.cn.common.dto.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultDto handle(Exception e){
        if(e instanceof SellException){
            SellException exception = (SellException) e;
            return ResultUtil.error(exception.getCode(),exception.getMessage());
        }else{
            log.error("系统异常:[{}]",e.getMessage());
            return ResultUtil.error(-1,"系统异常");
        }
    }
}
