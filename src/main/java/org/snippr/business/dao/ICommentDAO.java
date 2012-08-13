package org.snippr.business.dao;

import java.util.List;

import org.snippr.business.entities.Comment;

public interface ICommentDAO {

	List <Comment> getAll();
}
