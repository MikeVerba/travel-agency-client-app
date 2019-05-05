package com.example.travelagencyclientapp.app.models.services;

import com.example.travelagencyclientapp.app.models.entities.UserEntity;
import com.example.travelagencyclientapp.app.models.forms.LoginForm;
import com.example.travelagencyclientapp.app.models.forms.RegistrationForm;

public interface UserService {

    boolean addUser(RegistrationForm user);

    UserServiceImpl.LoginResponse login(LoginForm loginForm);

    UserEntity findById(Long id);

    void logoutUser();

    void updateUser(UserEntity userEntity);

    UserEntity getLoggedUser();

}
