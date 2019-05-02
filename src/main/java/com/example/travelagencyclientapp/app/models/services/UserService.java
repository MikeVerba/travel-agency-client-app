package com.example.travelagencyclientapp.app.models.services;

import com.example.travelagencyclientapp.app.models.entities.UserEntity;
import com.example.travelagencyclientapp.app.models.forms.UserForm;
import com.example.travelagencyclientapp.app.models.mappers.UserFormToUserEntityMapper;
import com.example.travelagencyclientapp.app.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    public enum LoginResponse {
        BANNED, SUCCESS, BAD_CREDENTIALS, ACCOUNT_NOT_ACTIVATED;
    }

    private final UserRepository userRepository;
    private final HashService hashService;
    private final UserSession userSession;

    @Autowired
    public UserService(UserRepository userRepository, HashService hashService, UserSession userSession) {
        this.userRepository = userRepository;
        this.hashService = hashService;
        this.userSession = userSession;
    }

    /**
     *
     * @param user
     * @return return true if user was added correctly, and false otherwise
     */
    public boolean addUser(UserForm user){
        if(!isUsernameFree(user.getName())){
            return false;
        }

        user.setPassword(hashService.hashPassword(user.getPassword()));
        return userRepository.save(new UserFormToUserEntityMapper().map(user)) != null;

    }

    private boolean isUsernameFree(String nick){
       return !userRepository.existsByUsername(nick);
    }


    public LoginResponse login(UserForm user){
        Optional<UserEntity> userWitchTryToLogin =
                userRepository.getUserByUsername(user.getName());

        if(!userWitchTryToLogin.isPresent() || !hashService.isPasswordCorrect(userWitchTryToLogin.get().getPassword(), user.getPassword())){
            return LoginResponse.BAD_CREDENTIALS;
        }
        if(userWitchTryToLogin.get().getAccountStatus() == UserEntity.AccountStatus.BANNED){
            return LoginResponse.BANNED;
        }else if(userWitchTryToLogin.get().getAccountStatus() == UserEntity.AccountStatus.NOT_ACTIVATED){
            return LoginResponse.ACCOUNT_NOT_ACTIVATED;
        }


        userSession.setLogin(true);
        userSession.setUserEntity(userWitchTryToLogin.get());
        return LoginResponse.SUCCESS;
    }



//
//    @Transactional
//    public void test() {
//        Optional<UserEntity> userEntity = userRepository.findById(userSession.getUserEntity().getId());
//        UserEntity withoutOptional = userEntity.get();
//
//        ContactEntity contactEntity = new ContactEntity();
//        contactEntity.setEmail("asd");
//        contactEntity.setSurname("asdasd");
//        contactEntity.setName("asdasdsad");
//        contactEntity.setPhone("213213213");
//        contactEntity.setUser(withoutOptional);
//
//        withoutOptional.getContacts().add(contactEntity);
//    }

}
