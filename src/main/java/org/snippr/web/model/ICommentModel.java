package org.snippr.web.model;

import java.util.List;

import org.snippr.business.entities.Comment;
import org.snippr.business.exceptions.DuplicateName;
import org.snippr.business.exceptions.InstanceNotFoundException;

/**
 *
 * @author Miguel Gonzalez <migonzalvar@gmail.com>
 *
 */
public interface ICommentModel {

    List<Comment> getAll();

    Comment getComment();

    void prepareForCreate();

    void prepareForEdit(Long id) throws InstanceNotFoundException;

    void save() throws DuplicateName;

    void setComment(Comment comment);

    void delete(Comment comment) throws InstanceNotFoundException;

}
