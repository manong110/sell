package com.inspur.cn.common.dto;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class ResultUtil extends ResultDto {

    public static ResultDto success(Object data){
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(0);
        resultDto.setMsg("成功");
        resultDto.setData(data);
        return resultDto;
    }

    public static ResultDto success(){
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(0);
        resultDto.setMsg("成功");
        return resultDto;
    }

    public static ResultDto error(Integer code,String msg){
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(code);
        resultDto.setMsg(msg);
        return resultDto;
    }
}
