package com.example.travelagencyclientapp.app.models.services;

import com.example.travelagencyclientapp.app.models.entities.UserEntity;
import com.example.travelagencyclientapp.app.models.forms.LoginForm;
import com.example.travelagencyclientapp.app.models.forms.RegistrationForm;
import com.example.travelagencyclientapp.app.models.mappers.UserFormToUserEntityMapper;
import com.example.travelagencyclientapp.app.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    public enum LoginResponse {
        BANNED, SUCCESS, BAD_CREDENTIALS, ACCOUNT_NOT_ACTIVATED;
    }

    private final UserRepository userRepository;
    private final HashService hashService;
    private final UserSession userSession;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, HashService hashService, UserSession userSession) {
        this.userRepository = userRepository;
        this.hashService = hashService;
        this.userSession = userSession;


    }

    /**
     * @param user
     * @return return true if user was added correctly, and false otherwise
     */
    @Override
    public boolean addUser(RegistrationForm user) {
        if (!isUsernameFree(user.getUsername())) {
            return false;
        }

        user.setPassword(hashService.hashPassword(user.getPassword()));
        return userRepository.save(new UserFormToUserEntityMapper().map(user)) != null;

    }

    private boolean isUsernameFree(String username) {
        return !userRepository.existsByUsername(username);
    }


    @Override
    public LoginResponse login(LoginForm loginForm) {
        Optional<UserEntity> userWitchTryToLogin =
                userRepository.getUserByUsername(loginForm.getUsername());

        if (!userWitchTryToLogin.isPresent() || !hashService.isPasswordCorrect(userWitchTryToLogin.get().getPassword(), loginForm.getPassword())) {
            return LoginResponse.BAD_CREDENTIALS;
        }
        if (userWitchTryToLogin.get().getAccountStatus() == UserEntity.AccountStatus.BANNED) {
            return LoginResponse.BANNED;
        } else if (userWitchTryToLogin.get().getAccountStatus() == UserEntity.AccountStatus.NOT_ACTIVATED) {
            return LoginResponse.ACCOUNT_NOT_ACTIVATED;
        }


        userSession.setLogin(true);
        userSession.setUserEntity(userWitchTryToLogin.get());
        return LoginResponse.SUCCESS;
    }


    @Override
    public UserEntity findById(Long id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        return userEntityOptional.orElse(null);
    }


    @Override
    public void logoutUser() {
        userSession.setLogin(false);
    }

    @Override
    public void updateUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public UserEntity getLoggedUser() {
        return userSession.getUserEntity();
    }

}
