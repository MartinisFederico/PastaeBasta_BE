package com.fandeni.pastaandbastaBE.customException;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String msg) {
        super(msg);
    }

    public UserAlreadyExistsException(){
        super();
    }
}
