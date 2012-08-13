package org.snippr.business.dao;

import java.util.List;
import org.snippr.business.entities.Comment;

/**
 *
 * @author Diego Bernardez
 *
 */

public interface ICommentDAO extends IGenericDAO<Comment, Long>{

	List <Comment> getAll();
	boolean exists(Comment comment);
}
