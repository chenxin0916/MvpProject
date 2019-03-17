package com.awesome.mvp.network;

/**
 * 服务器返回异常码处理
 */
public class ErrorHandler {

   public static final int RESPONSE_OK = 200;   //服务器返回code正常，根据自己协议更改
   public static final int RESPONSE_AUTHER_FAILEDC = -1;
   //..................

    public static void  handError(int erorCode){
        switch (erorCode){
            case RESPONSE_AUTHER_FAILEDC:
                //todo
                break;
                default:
                    //未知错误
                    break;
        }
    }


}
