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
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.snippr.business.entities.BaseEntity;
import org.snippr.business.exceptions.InstanceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * An implementation of <code>IGenericDao</code> based on Hibernate's native
 * API. Concrete DAOs must extend directly from this class. This constraint is
 * imposed by the constructor of this class that must infer the type of the
 * entity from the declaration of the concrete DAO.
 * <p/>
 * This class autowires a <code>SessionFactory</code> bean and allows to
 * implement DAOs with Hibernate's native API. Subclasses access Hibernate's
 * <code>Session</code> by calling on <code>getSession()</code> method.
 * Operations must be implemented by catching <code>HibernateException</code>
 * and rethrowing it by using <code>convertHibernateAccessException()</code>
 * method. See source code of this class for an example.
 *
 * @author Fernando Bellas Permuy <fbellas@udc.es>
 * @param <E>
 *            Entity class
 * @param <PK>
 *            Primary key class
 */
public class GenericDAOHibernate<E extends BaseEntity, PK extends Serializable>
        implements IGenericDAO<E, PK> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<E> entityClass;

    @SuppressWarnings("unchecked")
    public GenericDAOHibernate() {
        this.entityClass = (Class<E>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void save(E entity) {
        getSession().saveOrUpdate(entity);
    }

    public boolean exists(PK id) {
        return getSession().createCriteria(entityClass)
                .add(Restrictions.idEq(id)).setProjection(Projections.id())
                .uniqueResult() != null;
    }

    @SuppressWarnings("unchecked")
    public E find(PK id) throws InstanceNotFoundException {
        E entity = (E) getSession().get(entityClass, id);
        if (entity == null) {
            throw new InstanceNotFoundException(id, entityClass.getName());
        }
        return entity;
    }

    public void remove(PK id) throws InstanceNotFoundException {
        getSession().delete(find(id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends E> List<T> list(Class<T> klass) {
        return getSession().createCriteria(klass).list();
    }

    @Override
    public void reattach(E entity) {
        getSession().saveOrUpdate(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> getAll(Class<?> c, String orderBy) {
        Criteria criteria = getSession().createCriteria(c);
        criteria.addOrder(Order.asc(orderBy));
        return criteria.list();
    }

    @Override
    public List<E> getAll() {
        return getAll(BaseEntity.class, "id");
    }

}
