package org.snippr.business.dao;

import java.util.List;

import org.snippr.business.entities.User;

/**
 *
 * @author Antonio Arias <antonio.arias@gmail.com>
 *
 */
public interface IUserDAO extends IGenericDAO<User, Long> {

    List<User> getAll();

    User getByUsername(String username);

}
