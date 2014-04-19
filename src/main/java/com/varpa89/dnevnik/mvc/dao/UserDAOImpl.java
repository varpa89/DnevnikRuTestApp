package com.varpa89.dnevnik.mvc.dao;

import com.varpa89.dnevnik.mvc.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pasha on 19.04.2014.
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addUser(User user){
        sessionFactory.getCurrentSession().save(user);
    }

    //@SuppressWarnings("unchecked")
    public List<User> listUser(){
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    public void removeUser(Long id){
        User user = (User) sessionFactory.getCurrentSession().load(User.class, id);
        if(null != user){
            sessionFactory.getCurrentSession().delete(user);
        }
    }
}
