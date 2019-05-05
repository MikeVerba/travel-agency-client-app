package com.example.travelagencyclientapp.app.models.forms;

import javax.validation.constraints.Pattern;

public class LoginForm {

        @Pattern(regexp = "[A-Za-z0-9]{3,30}")
        private String username;

        @Pattern(regexp = "[A-Za-z0-9]{3,30}")
        private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
