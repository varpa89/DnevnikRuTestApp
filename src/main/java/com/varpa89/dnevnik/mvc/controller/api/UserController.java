package com.varpa89.dnevnik.mvc.controller.api;

import com.varpa89.dnevnik.mvc.model.User;
import com.varpa89.dnevnik.mvc.repository.UserRepository;
import com.varpa89.dnevnik.mvc.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * User: varpa89
 * Date: 18.04.14
 * Time: 16:28
 */
@Controller
@RequestMapping("/api/v1")
public class UserController {
//    @Autowired
//    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET, headers = {"Content-Type=application/json"})
    public
    @ResponseBody
    List<User> listUsersJson(ModelMap model) {
        List<User> userList = userService.listUser();
        return userList;
    }
}
