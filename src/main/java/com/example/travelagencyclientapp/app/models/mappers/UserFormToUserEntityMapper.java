package com.example.travelagencyclientapp.app.models.mappers;

import com.example.travelagencyclientapp.app.models.entities.UserEntity;
import com.example.travelagencyclientapp.app.models.forms.UserForm;
import org.springframework.stereotype.Service;

@Service
public class UserFormToUserEntityMapper extends Mapper<UserForm, UserEntity>{

    @Override
    public UserEntity map(UserForm key) {
        return null;
    }
}
