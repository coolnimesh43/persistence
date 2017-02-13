package com.coolnimesh43.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.coolnimesh43.persistence.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByLogin(String login);

    @Query(value = "Select * from user_table u where LOWER(email) = LOWER(?1)")
    User findByEmail(String email);
}
