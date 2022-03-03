package com.fandeni.pastaandbastaBE.customException;

import com.fandeni.pastaandbastaBE.model.Categoria;

public class CategoriaNotFoundException extends Exception{
    public CategoriaNotFoundException(String msg){
        super(msg);
    }
    public CategoriaNotFoundException(){
        super();
    }
}
