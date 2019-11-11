package com.fff.commons;


public class R {

    private   int  code;//1 成功    0 失败
    private  String  msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    R(int code){
        this.code = code;
    }
    R(int code, String  msgh){
        this.code = code;
        this.msg = msgh;
    }

    public   static R ok(){
        return new R(1);
    }
    public   static R ok(String  msg){
        return new R(1,msg);
    }

    public   static R error(){
        return new R(0);
    }

    public   static R error(String msg){
        return new R(0,msg);
    }
}
