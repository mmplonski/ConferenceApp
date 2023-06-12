package com.marcinplonski.conferenceapp.users.repository;

import com.marcinplonski.conferenceapp.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByLogin(String login);
    User findUserByLogin(String login);
}
