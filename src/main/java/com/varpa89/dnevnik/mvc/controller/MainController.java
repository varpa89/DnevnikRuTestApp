package com.varpa89.dnevnik.mvc.controller;

import com.varpa89.dnevnik.mvc.model.User;
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


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        return "index";
    }
}
