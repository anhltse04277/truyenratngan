package com.techkid.anh82.demoretrofit;

/**
 * Created by anh82 on 5/23/2017.
 */

public class RegisterResponse {
    private String message ;
    private int code;

    public RegisterResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
