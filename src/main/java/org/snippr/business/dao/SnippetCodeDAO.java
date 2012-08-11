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

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.snippr.business.entities.SnippetCode;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


/**
 * Database DAO class for SnippetCode
 *
 * @author Jorge Muñoz Castañer
 * @version 20120811
 *
 */
@Repository
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class SnippetCodeDAO extends GenericDAOHibernate<SnippetCode, Long>
        implements ISnippetCodeDAO {

    /*
     * (non-Javadoc)
     *
     * @see org.snippr.business.dao.ISnippetCodeDAO#getAll()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SnippetCode> getAll() {
        Criteria criteria = getSession().createCriteria(SnippetCode.class);
        criteria.addOrder(Order.asc("id"));
        return criteria.list();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.snippr.business.dao.ISnippetCodeDAO#exists(org.snippr.business.entities
     * .SnippetCode)
     */
    @Override
    public boolean exists(SnippetCode snippetCode) {
        Criteria criteria = getSession().createCriteria(SnippetCode.class);
        criteria.add(Restrictions.eq("code", snippetCode.getCode()));
        return !criteria.list().isEmpty();
    }

}
