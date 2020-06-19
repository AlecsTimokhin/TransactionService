package com.myorg.mainpack.dto;


public class RestResponce {

    private String message;


    public RestResponce(){}

    public RestResponce(String message){
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
