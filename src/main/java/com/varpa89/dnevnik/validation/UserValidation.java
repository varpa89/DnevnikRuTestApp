package com.varpa89.dnevnik.validation;

import com.varpa89.dnevnik.mvc.model.User;
import com.varpa89.dnevnik.mvc.service.UserService;
import org.springframework.validation.BindingResult;

public class UserValidation {
    public void validateUser(User user, BindingResult errors, UserService userService) {
        if (user != null) {
            if (userService.isExistsByLogin(user.getLogin(), user.getId())) {
                errors.rejectValue("login", "error.login.notUnique", "Введенный логин уже существует");
            }
            if (isEmpty(user.getLogin())) {
                errors.rejectValue("login", "error.login.isEmpty", "Необходимо ввести логин");
            }
            if (isEmpty(user.getLastName())) {
                errors.rejectValue("lastName", "error.lastName.isEmpty", "Необходимо ввести фамилию");
            }
            if (isEmpty(user.getFirstName())) {
                errors.rejectValue("firstName", "error.firstName.isEmpty", "Необходимо ввести имя");
            }
            if (user.getId() == null) {
                if (isEmpty(user.getPassword()))
                    errors.rejectValue("password", "error.password.isEmpty", "Необходимо ввести пароль");
                if (isEmpty(user.getPassword2()))
                    errors.rejectValue("password2", "error.password2.isEmpty", "Повторите пароль");
            }
            if (!isEmpty(user.getPassword()) && user.getPassword().trim().length() < 5)
                errors.rejectValue("password", "error.password.size", "Пароль должен быть не менее 5 символов");
            else if (!isEmpty(user.getPassword()) && !isEmpty(user.getPassword2()) && !user.getPassword().equals(user.getPassword2())) {
                errors.rejectValue("password", "error.password.notEquals", "Пароли не совпадают");
                errors.rejectValue("password2", "error.password2.notEquals", "Пароли не совпадают");
            } else if(!user.getPassword().equals(user.getPassword2())){
                errors.rejectValue("password", "error.password.notEquals", "Пароли не совпадают");
                errors.rejectValue("password2", "error.password2.notEquals", "Пароли не совпадают");
            }
        }
    }

    public Boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }


}
