package com.varpa89.dnevnik.mvc.controller.api;

import com.varpa89.dnevnik.mvc.model.User;
import com.varpa89.dnevnik.mvc.repository.UserRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: varpa89
 * Date: 18.04.14
 * Time: 16:28
 */
@Controller
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/users", method = RequestMethod.GET, headers = {"Content-Type=application/json"})
    public
    @ResponseBody
    String listUsersJson(ModelMap model) throws JSONException {
        JSONArray userArray = new JSONArray();
        for (User user : userRepository.findAll()) {
            JSONObject userJSON = new JSONObject();
            userJSON.put("id", user.getId());
            userJSON.put("firstName", user.getFirstName());
        }
        return userArray.toString();
    }
}
