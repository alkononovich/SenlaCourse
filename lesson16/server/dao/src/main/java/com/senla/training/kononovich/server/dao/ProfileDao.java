package com.senla.training.kononovich.server.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.senla.training.kononovich.server.api.dao.IProfileDao;
import com.senla.training.kononovich.server.model.Profile;
import com.senla.training.kononovich.server.model.User;

@Repository
public class ProfileDao extends AbstractDao<Profile> implements IProfileDao{
    
    public ProfileDao() {
        super(Profile.class);
    }

    public Profile getDataByUser(User user) {

    	Profile userData = (Profile) getSession().createCriteria(Profile.class)
                .add(Restrictions.eq("id", user.getId()))
                .uniqueResult();
        return userData;
    }
}
