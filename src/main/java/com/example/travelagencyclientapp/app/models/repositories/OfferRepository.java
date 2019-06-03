package com.example.travelagencyclientapp.app.models.repositories;

import com.example.travelagencyclientapp.app.models.entities.OfferEntity;
import com.example.travelagencyclientapp.app.models.entities.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity,Long> {

    List<OfferEntity> findByUserEntity(UserEntity entity);
}
