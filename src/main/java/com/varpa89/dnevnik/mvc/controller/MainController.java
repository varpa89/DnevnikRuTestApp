package com.varpa89.dnevnik.mvc.controller;

import com.varpa89.dnevnik.mvc.model.User;
import com.varpa89.dnevnik.mvc.repository.UserRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * User: varpa89
 * Date: 18.04.14
 * Time: 15:49
 */
@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, BindingResult result) {
        userRepository.save(user);

        return "redirect:/";
    }

    @RequestMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") Long userId) {
        userRepository.delete(userRepository.findOne(userId));
        return "redirect:/";
    }


}
