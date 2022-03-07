package com.fandeni.pastaandbastaBE.customException;

public class LikeException extends Exception{
    public LikeException(String msg){
        super(msg);
    }

    public LikeException(){
        super();
    }
}
