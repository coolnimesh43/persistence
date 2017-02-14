package com.coolnimesh43.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.coolnimesh43.persistence.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByLogin(String login);

    @Query(value = "Select u.* from user_table u where LOWER(u.email) = LOWER(?1)", nativeQuery = true)
    User findByEmail(String email);
}
