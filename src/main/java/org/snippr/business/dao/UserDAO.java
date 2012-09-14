/*
 * This file is part of Snippr
 *
 * Copyright (C) 2012 Igalia, S.L.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.snippr.business.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.snippr.business.entities.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.spring.security.SecurityUtil;

/**
 *
 * @author Antonio Arias <antonio.arias@gmail.com>
 *
 */
@Repository
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class UserDAO extends GenericDAOHibernate<User, Long> implements
        IUserDAO {

    @Override
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        Authentication auth = SecurityUtil.getAuthentication();
        return getByUsername(auth.getName());
    }

    @Override
    @Transactional(readOnly = true)
    public User getByUsername(String username) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("username", username));
        criteria.createCriteria("roles");
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
