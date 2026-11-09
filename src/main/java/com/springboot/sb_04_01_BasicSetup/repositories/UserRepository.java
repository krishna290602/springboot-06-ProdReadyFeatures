package com.springboot.sb_04_01_BasicSetup.repositories;

import com.springboot.sb_04_01_BasicSetup.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
