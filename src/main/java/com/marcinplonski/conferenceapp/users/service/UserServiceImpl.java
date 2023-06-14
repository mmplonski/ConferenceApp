package com.marcinplonski.conferenceapp.users.service;

import com.marcinplonski.conferenceapp.users.exception.UserError;
import com.marcinplonski.conferenceapp.users.exception.UserException;
import com.marcinplonski.conferenceapp.users.model.User;
import com.marcinplonski.conferenceapp.users.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
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
        validateUserEmail(user);
        return userRepository.save(user);
    }

    private void validateUserEmail(User user) {
        User userByEmail = userRepository.getUserByEmail(user.getEmail());
        if (userByEmail != null && (!user.getLogin().equals(userByEmail.getLogin()))) {
            throw new UserException(UserError.USER_EMAIL_TAKEN);
        }
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
            if (!ObjectUtils.isEmpty(user.getEmail())) {
                userFromDB.setEmail(user.getEmail());
            }
            return userRepository.save(userFromDB);
        }).orElseThrow(() -> new UserException(UserError.USER_NOT_FOUND));
    }

    @Override
    public boolean isUserExistsByLoginAndEmail(String login, String email) {
        return userRepository.existsUserByLoginAndEmail(login, email);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }

}
