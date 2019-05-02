package com.example.travelagencyclientapp.app.models.repositories;

import com.example.travelagencyclientapp.app.models.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long> {

    boolean existsByUsername(String username);

    Optional<UserEntity> getUserByUsername(String username);

}
