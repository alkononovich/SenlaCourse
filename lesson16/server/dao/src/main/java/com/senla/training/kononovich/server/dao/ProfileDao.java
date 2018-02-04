package com.senla.training.kononovich.server.dao;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.senla.training.kononovich.server.model.Profile;
import com.senla.training.kononovich.server.model.User;

@Repository
public class ProfileDao extends AbstractDao<Profile> {
    @Autowired
    public ProfileDao() {
        super(Profile.class);
    }
    public Profile getProfile(User user){

    	Profile profile = (Profile) getSession().createCriteria(Profile.class)
                .add(Restrictions.eq("user", user))
                .uniqueResult();
        return profile;

    }
}
