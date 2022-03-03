package com.fandeni.pastaandbastaBE.customException;

public class ReactionDoesntExistsException extends Exception {
    public ReactionDoesntExistsException(String msg) {
        super(msg);
    }

    public ReactionDoesntExistsException(){
        super();
    }
}
