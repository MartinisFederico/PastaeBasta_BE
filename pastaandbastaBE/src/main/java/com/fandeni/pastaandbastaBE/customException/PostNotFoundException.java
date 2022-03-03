package com.fandeni.pastaandbastaBE.customException;

public class PostNotFoundException extends Exception{
    public PostNotFoundException(String msg){
        super(msg);
    }

    public PostNotFoundException(){
        super();
    }
}
