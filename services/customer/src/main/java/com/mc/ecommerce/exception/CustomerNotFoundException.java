package com.mc.ecommerce.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;


public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String msg ){
        super(msg);//calling the constructor of the parent class RuntimeException
        //remember : when you have an exception you need to handle it  , and  I handle it in the handler
    }
}
