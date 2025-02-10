package com.bruceycode.My_Rest_Api.exceptions;

public class EmailTakenException extends RuntimeException{

    public EmailTakenException(String message) {
        super(message);
    }

}
