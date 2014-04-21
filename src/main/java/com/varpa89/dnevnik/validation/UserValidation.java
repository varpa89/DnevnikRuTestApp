package com.varpa89.dnevnik.validation;

import com.varpa89.dnevnik.mvc.model.User;
import com.varpa89.dnevnik.mvc.service.UserService;
import org.springframework.validation.BindingResult;

public class UserValidation {
    public void validateUser(User user, BindingResult errors, UserService userService) {
        if (user != null) {
            if (userService.isExistsByLogin(user.getLogin(), user.getId())) {
                errors.rejectValue("login", "error.login.notUnique", "error.login.notUnique");
            }
            if (user.getLogin().trim().isEmpty()) {
                errors.rejectValue("login", "error.login.isEmpty", "Необходимо ввести логин");
            }
            if (user.getLastName().trim().isEmpty()) {
                errors.rejectValue("lastName", "error.lastName.isEmpty", "Необходимо ввести фамилию");
            }
            if (user.getFirstName().trim().isEmpty()) {
                errors.rejectValue("firstName", "error.firstName.isEmpty", "Необходимо ввести имя");
            }
            if (user.getId() == null) {
                if (passwordIsEmpty(user.getPassword()))
                    errors.rejectValue("password", "error.password.isEmpty", "Необходимо ввести пароль");
                if (passwordIsEmpty(user.getPassword2()))
                    errors.rejectValue("password2", "error.password2.isEmpty", "Повторите пароль");

                if (!passwordIsEmpty(user.getPassword()) && user.getPassword().trim().length() < 5)
                    errors.rejectValue("password", "error.password.size", "Пароль должен быть не менее 5 символов");
                else if (!passwordIsEmpty(user.getPassword()) && !passwordIsEmpty(user.getPassword2()) && !user.getPassword().equals(user.getPassword2())) {
                    errors.rejectValue("password", "error.password.notEquals", "Пароли не совпадают");
                    errors.rejectValue("password2", "error.password2.notEquals", "Пароли не совпадают");
                }
            } else {
                if (!passwordIsEmpty(user.getPassword()) && user.getPassword().trim().length() < 5)
                    errors.rejectValue("password", "error.password.size", "Пароль должен быть не менее 5 символов");
                else if (!passwordIsEmpty(user.getPassword()) && !passwordIsEmpty(user.getPassword2()) && !user.getPassword().equals(user.getPassword2())) {
                    errors.rejectValue("password", "error.password.notEquals", "Пароли не совпадают");
                    errors.rejectValue("password2", "error.password2.notEquals", "Пароли не совпадают");
                }

            }
        }
    }

    public Boolean passwordIsEmpty(String password) {
        return password == null || password.trim().isEmpty();
    }


}
