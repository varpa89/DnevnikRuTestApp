package com.varpa89.dnevnik.mvc.repository;

import com.varpa89.dnevnik.mvc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: varpa89
 * Date: 18.04.14
 * Time: 15:43
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
