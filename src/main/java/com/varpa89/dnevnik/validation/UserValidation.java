package com.varpa89.dnevnik.validation;

import com.varpa89.dnevnik.mvc.model.User;
import org.springframework.validation.BindingResult;

/**
 * User: varpa89
 * Date: 21.04.14
 * Time: 10:36
 */
public class UserValidation {
    public void validateUserOnCreate(User user, BindingResult errors) {
        if (user != null) {
            if("varpa89".equals(user.getLogin())){
                errors.rejectValue("login", "error.login.notUnique","error.login.notUnique");
            }
        }
    }
}
