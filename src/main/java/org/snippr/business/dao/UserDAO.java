package org.snippr.business.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.snippr.business.entities.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Antonio Arias <antonio.arias@gmail.com>
 * 
 */

@Repository
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class UserDAO extends GenericDAOHibernate<User, Long> implements
        IUserDAO {

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAll() {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.addOrder(Order.asc("id"));
        return criteria.list();
    }

    @Override
    @Transactional(readOnly = true)
    public User getByUsername(String username) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("username", username));
        criteria.setMaxResults(1);
        User user = null;
        if (!criteria.list().isEmpty()) {
            user = (User) criteria.list().get(0);
        } else {
            user = new User().invalidate();
        }

        return user;
    }

}
