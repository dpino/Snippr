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

import java.io.Serializable;
import java.util.List;

import org.snippr.business.entities.Snippet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.SessionImplementor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Database DAO class for Snippet
 *
 * @author José Manuel Ciges Regueiro
 * @version 20120803
 *
 */
@Repository
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class SnippetDAO extends GenericDAOHibernate<Snippet, Long> implements
        ISnippetDAO {

    /* (non-Javadoc)
     * @see org.snippr.business.dao.ISnippetDAO#getAll()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Snippet> getAll() {
        Criteria criteria = getSession().createCriteria(Snippet.class);
        criteria.addOrder(Order.asc("title"));
        return criteria.list();
    }

    /* (non-Javadoc)
     * @see org.snippr.business.dao.ISnippetDAO#exists(org.snippr.business.entities.Snippet)
     */
    @Override
    public boolean exists(Snippet snippet) {
        Criteria criteria = getSession().createCriteria(Snippet.class);
        criteria.add(Restrictions.eq("title", snippet.getTitle()));
        return !criteria.list().isEmpty();
    }

    @Override
    public Serializable generate(SessionImplementor session, Object object)
            throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

}
