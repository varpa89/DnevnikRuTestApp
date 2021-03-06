package com.varpa89.dnevnik.mvc.controller.api;

import com.varpa89.dnevnik.exception.InvalidRequestException;
import com.varpa89.dnevnik.mvc.model.User;
import com.varpa89.dnevnik.mvc.service.UserService;
import com.varpa89.dnevnik.util.SecurityBean;
import com.varpa89.dnevnik.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityBean securityBean;

    UserValidation validator = new UserValidation();

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
    User createUser(@RequestBody User user, BindingResult result){
        validator.validateUser(user, result, userService);
        if(result.hasErrors()){
            throw new InvalidRequestException("Invalid user", result);
        }
        user.setPassword(securityBean.getStringMD5fromString(user.getPassword()));
        userService.addUser(user);
        return user;
    }

    @RequestMapping(value="/users/{id}", method=RequestMethod.PUT, headers = {"Content-Type=application/json"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public
    @ResponseBody
    User updateUser(@PathVariable("id") Long userId, @RequestBody User user, BindingResult result){
        validator.validateUser(user, result, userService);
        if(result.hasErrors()){
            throw new InvalidRequestException("Invalid user", result);
        }
        if(!validator.isEmpty(user.getPassword())){
            user.setPassword(securityBean.getStringMD5fromString(user.getPassword()));
        } else {
            //TODO: it is bad practice
            User existedUser = userService.getUser(userId);
            user.setPassword(existedUser.getPassword());
        }
        userService.updateUser(user);
        return user;
    }

    @RequestMapping(value="/users/{id}", method=RequestMethod.DELETE, headers = {"Content-Type=application/json"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public
    @ResponseBody
    void removeUser(@PathVariable("id") Long userId){
        userService.removeUser(userId);
    }

    @RequestMapping(value="/users/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    User getUser(@PathVariable("id") Long userId){
        return userService.getUser(userId);
    }
}
