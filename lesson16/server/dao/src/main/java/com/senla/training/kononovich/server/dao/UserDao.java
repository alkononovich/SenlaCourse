package com.senla.training.kononovich.server.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.senla.training.kononovich.server.api.dao.IUserDao;
import com.senla.training.kononovich.server.model.User;

@Repository
public class UserDao extends AbstractDao<User> implements IUserDao {
	
    public UserDao() {
        super(User.class);
    }

    private static Logger log = Logger.getLogger(UserDao.class);

    public Long checkUser(String login,String password) throws Exception{
            Criteria criteria = getSession().createCriteria(User.class)
                    .add(Restrictions.like("login",login))
                    .add(Restrictions.like("password", password));
            User checkingUser = (User) criteria.uniqueResult();
            if (checkingUser != null) {
                return checkingUser.getId();
            }
                return null;
    }

    public User getUserByLoginPassword(String login, String password) throws Exception {
            Criteria criteria = getSession().createCriteria(User.class)
                    .add(Restrictions.like("login", login))
                    .add(Restrictions.like("password", password));
            User user = (User) criteria.uniqueResult();
            return user;
        }

}
