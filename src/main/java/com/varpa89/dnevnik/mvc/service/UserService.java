package com.varpa89.dnevnik.mvc.service;

import com.varpa89.dnevnik.mvc.model.User;

import java.util.List;

/**
 * Created by pasha on 19.04.2014.
 */
public interface UserService {
    public void addUser(User user);

    public List<User> listUser();

    public void removeUser(Long id);

    public User getUser(Long id);

    public User updateUser(User user);

    public Boolean isExistsByLogin(String login, Long userId);
}
