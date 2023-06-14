package com.marcinplonski.conferenceapp.users.service;

import com.marcinplonski.conferenceapp.prelections.model.Prelection;
import com.marcinplonski.conferenceapp.users.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User getUser(Long id);

    User addUser(User user);

    void deleteUser(Long id);

    User patchUser(Long id, User user);

    boolean isUserExistsByLoginAndEmail(String login, String email);

    User getUserByEmail(String email);

    User getUserByLogin(String login);

    Long countAllUsers();

}
