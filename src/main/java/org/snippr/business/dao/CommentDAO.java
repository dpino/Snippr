package org.snippr.business.dao;

import java.util.List;
import org.snippr.business.entities.Comment;

/**
 *
 * @author Diego Bernardez
 *
 */

public class CommentDAO extends GenericDAOHibernate<Comment, Long> implements ICommentDAO{

	@Override
	public List<Comment> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(Comment comment) {
		// TODO Auto-generated method stub
		return false;
	}
}
