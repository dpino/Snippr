package org.snippr.business.dao;

import java.util.List;

import org.snippr.business.entities.Comment;
import org.snippr.business.entities.Snippet;

/**
 *
 * @author Diego Bernardez
 *
 */

public interface ICommentDAO extends IGenericDAO<Comment, Long>{

	@Override
    List <Comment> getAll();
	boolean exists(Comment comment);

    List<Comment> getAllBySnippet(Snippet snippet);
}
