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
            if(user.getLogin().trim().isEmpty()){
                errors.rejectValue("login", "error.login.isEmpty","Введите логин");
            }
            if(user.getLastName().trim().isEmpty()){
                errors.rejectValue("lastName", "error.lastName.isEmpty","Введите фамилию");
            }
            if(user.getFirstName().trim().isEmpty()){
                errors.rejectValue("firstName", "error.firstName.isEmpty","Введите имя");
            }
        }
    }
}
