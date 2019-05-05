package com.example.travelagencyclientapp.app.models.mappers;

import com.example.travelagencyclientapp.app.models.entities.UserEntity;
import com.example.travelagencyclientapp.app.models.forms.RegistrationForm;
import org.springframework.stereotype.Service;

@Service
public class UserFormToUserEntityMapper extends Mapper<RegistrationForm, UserEntity>{

    @Override
    public UserEntity map(RegistrationForm key) {

        UserEntity userEntity = new UserEntity();
        userEntity.setName(key.getName());
        userEntity.setSurname(key.getSurname());
        userEntity.setUsername(key.getUsername());
        userEntity.setPassword(key.getPassword());
        userEntity.setEmail(key.getEmail());

        return userEntity;
    }
}
