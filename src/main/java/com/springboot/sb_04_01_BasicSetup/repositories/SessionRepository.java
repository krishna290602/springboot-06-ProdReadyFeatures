package com.springboot.sb_04_01_BasicSetup.repositories;

import com.springboot.sb_04_01_BasicSetup.entities.SessionEntity;
import com.springboot.sb_04_01_BasicSetup.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Long> {

    List<SessionEntity> findByUser(UserEntity user);

    Optional<SessionEntity> findByRefreshToken(String refreshToken);
}
