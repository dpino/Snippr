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
import org.snippr.business.entities.Label;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * @author Iago Lluque <iago.lluque@gmail.com>
 */
@Repository
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class LabelDAO extends GenericDAOHibernate<Label, Long> implements
        ILabelDAO {

    @Override
    public List<Label> getAll() {
        Criteria criteria = getSession().createCriteria(Label.class);
        criteria.addOrder(Order.asc("id"));
        return criteria.list();
    }

    @Override
    public boolean exists(Label label) {
        Criteria criteria = getSession().createCriteria(Label.class);
        criteria.add(Restrictions.eq("name", label.getName()));
        return !criteria.list().isEmpty();
    }

}