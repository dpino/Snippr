package org.snippr.web.model;

import java.util.List;

import org.snippr.business.dao.ICommentDAO;
import org.snippr.business.entities.Comment;
import org.snippr.business.exceptions.DuplicateName;
import org.snippr.business.exceptions.InstanceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Miguel Gonzalez <migonzalvar@gmail.com>
 *
 */
@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CommentModel implements ICommentModel {

    @Autowired
    private ICommentDAO commentDAO;

    private Comment comment;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAll() {
        List<Comment> comments = commentDAO.getAll();
        return comments;
    }

    @Override
    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public Comment getComment() {
        return comment;
    }

    @Override
    @Transactional
    public void save() throws DuplicateName {
        if (comment.getId() == null && commentDAO.exists(comment)) {
            throw new DuplicateName();
        }
        commentDAO.save(comment);
    }

    @Override
    public void prepareForCreate() {
        comment = new Comment();
    }

    @Override
    @Transactional(readOnly = true)
    public void prepareForEdit(Long id) throws InstanceNotFoundException {
        comment = commentDAO.find(id);
    }

    @Override
    @Transactional
    public void delete(Comment comment) throws InstanceNotFoundException {
        commentDAO.remove(comment.getId());
    }

}