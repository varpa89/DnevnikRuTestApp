package com.varpa89.dnevnik.mvc.controller.api;

import com.varpa89.dnevnik.mvc.model.User;
import com.varpa89.dnevnik.mvc.repository.UserRepository;
import com.varpa89.dnevnik.mvc.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User: varpa89
 * Date: 18.04.14
 * Time: 16:28
 */
@Controller
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET, headers = {"Content-Type=application/json"})
    public
    @ResponseBody
    List<User> listUsersJson(ModelMap model) {
        List<User> userList = userService.listUser();
        return userList;
    }

    @RequestMapping(value="/users", method=RequestMethod.POST, headers = {"Content-Type=application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    User createUser(@RequestBody User user){
        userService.addUser(user);
        return user;
    }

    @RequestMapping(value="/users/{id}", method=RequestMethod.PUT, headers = {"Content-Type=application/json"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public
    @ResponseBody
    User updateUser(@PathVariable("id") Long userId, @RequestBody User user){
        userService.updateUser(user);
        return user;
    }

    @RequestMapping(value="/users/{id}", method=RequestMethod.DELETE, headers = {"Content-Type=application/json"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public
    @ResponseBody
    void updateUser(@PathVariable("id") Long userId){
        userService.removeUser(userId);
    }

    @RequestMapping(value="/users/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    User getUser(@PathVariable("id") Long userId){
        return userService.getUser(userId);
    }
}
