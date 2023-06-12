package com.marcinplonski.conferenceapp.users.service;

import com.marcinplonski.conferenceapp.users.exception.UserError;
import com.marcinplonski.conferenceapp.users.exception.UserException;
import com.marcinplonski.conferenceapp.users.model.User;
import com.marcinplonski.conferenceapp.users.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserException(UserError.USER_NOT_FOUND));
    }

    @Override
    public User addUser(User user) {
        validateUserLogin(user);
        return userRepository.save(user);
    }

    private void validateUserLogin(User user) {
        User userByLogin = userRepository.findUserByLogin(user.getLogin());
        if (userByLogin != null && (!user.getEmail().equals(userByLogin.getEmail()))) {
                throw new UserException(UserError.USER_LOGIN_TAKEN);
            }
    }


    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException(UserError.USER_NOT_FOUND));
        userRepository.delete(user);
    }

    @Override
    public User patchUser(Long id, User user) {
        return userRepository.findById(id).map(userFromDB -> {
            userFromDB.setEmail(user.getEmail());
            return userRepository.save(userFromDB);
        }).orElseThrow(() -> new UserException(UserError.USER_NOT_FOUND));
    }
}