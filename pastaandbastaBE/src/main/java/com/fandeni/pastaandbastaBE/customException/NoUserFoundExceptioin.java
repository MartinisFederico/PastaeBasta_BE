package com.fandeni.pastaandbastaBE.customException;

public class NoUserFoundExceptioin extends Exception{
    public NoUserFoundExceptioin(String message) {
        super(message);
    }

    public NoUserFoundExceptioin(){
        super();
    }

}
