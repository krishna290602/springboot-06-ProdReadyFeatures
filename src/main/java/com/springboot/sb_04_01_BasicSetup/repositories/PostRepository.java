package com.springboot.sb_04_01_BasicSetup.repositories;

import com.springboot.sb_04_01_BasicSetup.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}