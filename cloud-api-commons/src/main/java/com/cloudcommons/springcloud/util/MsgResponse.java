package com.cloudcommons.springcloud.util;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MsgResponse {
    private Integer status;
    private String msg;
    private Object data;

    public static MsgResponse success(String msg,Object data){
        MsgResponse response = new MsgResponse();
        response.setMsg(msg);
        response.setStatus(200);
        response.setData(data);
        return response;
    }

    public static MsgResponse error(String msg){
        MsgResponse response = new MsgResponse();
        response.setMsg(msg);
        response.setStatus(500);
        response.setData(null);
        return response;
    }

    public static MsgResponse unlogin(){
        MsgResponse response = new MsgResponse();
        response.setStatus(400);
        response.setData(null);
        response.setMsg("未登录，请先登陆");
        return response;
    }
}
