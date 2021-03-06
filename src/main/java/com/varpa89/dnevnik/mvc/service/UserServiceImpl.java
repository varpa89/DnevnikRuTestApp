package com.varpa89.dnevnik.mvc.service;

import com.varpa89.dnevnik.mvc.dao.UserDAO;
import com.varpa89.dnevnik.mvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pasha on 19.04.2014.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Transactional
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Transactional
    public List<User> listUser() {
        return userDAO.listUser();
    }

    @Transactional
    public void removeUser(Long id) {
        userDAO.removeUser(id);
    }

    @Transactional
    public User getUser(Long id) {
        return userDAO.getUser(id);
    }

    @Transactional
    public User updateUser(User user) {
        return userDAO.updateUser(user);
    }

    @Transactional
    public Boolean isExistsByLogin(String login, Long userId) {
        if (userId == null) {
            return userDAO.isExistsByLogin(login);
        } else {
            return userDAO.isExistsByLoginAndId(login, userId);
        }
    }
}
