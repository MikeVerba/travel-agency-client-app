package com.example.travelagencyclientapp.app.models.services;

import com.example.travelagencyclientapp.app.models.entities.UserEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSession {
    private boolean isLogin;
    private UserEntity userEntity; //todo hibernate proxy potential issue?


    public void logout() {
        isLogin = false;
        userEntity = null;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
