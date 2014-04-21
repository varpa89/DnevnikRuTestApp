package com.varpa89.dnevnik.mvc.dao;

import com.varpa89.dnevnik.mvc.model.User;
import org.hibernate.Query;
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

    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @SuppressWarnings("unchecked")
    public List<User> listUser() {
        return sessionFactory.getCurrentSession().createQuery("from User u where u.deleted=false").list();
    }

    public void removeUser(Long id) {
        User user = (User) sessionFactory.getCurrentSession().load(User.class, id);
        if (null != user) {
            user.setDeleted(true);
            sessionFactory.getCurrentSession().merge(user);
        }
    }

    public User getUser(Long id) {
        return (User) sessionFactory.getCurrentSession().get(User.class, id);
    }

    public User updateUser(User user) {
        return (User) sessionFactory.getCurrentSession().merge(user);
    }

    public Boolean isExistsByLogin(String login) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("select 1 from User u where u.login = :login and u.deleted = false");
        query.setString("login", login);
        return (query.uniqueResult() != null);
    }

    public Boolean isExistsByLoginAndId(String login, Long userId) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("select 1 from User u where u.login = :login and u.deleted = false and u.id <> :id");
        query.setString("login", login);
        query.setLong("id", userId);
        return (query.uniqueResult() != null);
    }
}
