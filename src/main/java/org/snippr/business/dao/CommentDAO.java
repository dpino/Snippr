package org.snippr.business.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.snippr.business.entities.Comment;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Diego Bernardez
 *
 */

@Repository
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class CommentDAO extends GenericDAOHibernate<Comment, Long> implements ICommentDAO{

	@Override
	public List<Comment> getAll() {
		Criteria criteria = getSession().createCriteria(Comment.class);
        criteria.addOrder(Order.asc("id"));
        return criteria.list();
	}

	@Override
	public boolean exists(Comment comment) {
		Criteria criteria = getSession().createCriteria(Comment.class);
        criteria.add(Restrictions.eq("id", comment.getId()));
        return !criteria.list().isEmpty();
	}
}
