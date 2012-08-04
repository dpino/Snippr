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

import org.snippr.business.exceptions.InstanceNotFoundException;
import org.springframework.dao.OptimisticLockingFailureException;

/**
 * The interface all DAOs (Data Access Objects) must implement. In general, a
 * DAO must be implemented for each persistent entity. Concrete DAOs may provide
 * (and usually will provide) additional methods.
 *
 * @author Diego Pino García <dpino@igalia.com> *
 * @param <E>
 *            Entity class
 * @param <PK>
 *            Primary key class
 */
public interface IGenericDAO<E, PK extends Serializable> {

    void save(E entity);

    void remove(PK id) throws InstanceNotFoundException;

    boolean exists(PK id);

    E find(PK id) throws InstanceNotFoundException;

    public <T extends E> List<T> list(Class<T> klass);

    /**
     * It reattaches the entity to the current session. This method bypasses
     * hibernate validations and must only be used on read only transaction
     * {@link OptimisticLockingFailureException} can be thrown if the entity has
     * been updated for another transaction
     *
     * @param entity
     */
    public void reattach(E entity);

    List<E> getAll(Class<?> c, String orderBy);

    List<E> getAll();

}
