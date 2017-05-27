package com.techkid.anh82.demoretrofit;

/**
 * Created by anh82 on 5/23/2017.
 */

public class RegisterRequest {
    private String username;
    private String password;

    public RegisterRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
