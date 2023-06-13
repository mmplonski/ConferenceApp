package com.marcinplonski.conferenceapp.users.repository;

import com.marcinplonski.conferenceapp.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByLogin(String login);
    boolean existsById(Long id);
    boolean existsByEmail(String email);
    boolean existsUserByLoginAndEmail(String login, String email);
    User getUserByEmail(String email);

    User getUserByLogin(String login);
}
