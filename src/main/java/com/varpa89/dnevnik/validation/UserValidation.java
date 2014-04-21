package com.varpa89.dnevnik.validation;

import com.varpa89.dnevnik.mvc.model.User;
import com.varpa89.dnevnik.mvc.service.UserService;
import org.springframework.validation.BindingResult;

public class UserValidation {
    public void validateUser(User user, BindingResult errors, UserService userService) {
        if (user != null) {
            if(userService.isExistsByLogin(user.getLogin(), user.getId())){
                errors.rejectValue("login", "error.login.notUnique","error.login.notUnique");
            }
        }
    }
}
