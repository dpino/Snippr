package org.snippr.web.controllers;

import java.io.IOException;
import java.util.List;

import org.snippr.business.entities.Comment;
import org.snippr.business.exceptions.DuplicateName;
import org.snippr.business.exceptions.InstanceNotFoundException;
import org.snippr.web.common.Notificator;
import org.snippr.web.common.OnlyOneVisible;
import org.snippr.web.common.Util;
import org.snippr.web.model.ICommentModel;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Label;
import org.zkoss.zul.Tab;
import org.zkoss.zul.api.Window;

/**
 *
 * @author Diego Bernardez
 *
 */

public class CommentCRUDController extends GenericForwardComposer{
	private OnlyOneVisible visibility;

    private Label notificationMessage;

    private Window listWindow;

    private Window editWindow;

    private Notificator notificator;

    private ICommentModel commentModel;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
				super.doAfterCompose(comp);
        comp.setAttribute("controller", this);
        notificator = Notificator.create(notificationMessage);
    }

    public List<Comment>getComments(){
				return commentModel.getAll();
    }

    public Comment getComment(){
				return commentModel.getComment();
    }

    public void delete(Comment comment) throws InstanceNotFoundException {
        commentModel.delete(comment);
        Util.reloadBindings(listWindow);
    }

    public void openCreateForm() {
        commentModel.prepareForCreate();
        showEditWindow("New Comment");
    }

    public void cancel() throws IOException {
        Executions.sendRedirect("/comments/comments.zul");
    }

    public void saveAndExit() throws IOException {
        try {
            commentModel.save();
            cancel();
        } catch (DuplicateName e) {
            notificator.error("Duplicated Comment");
        }
    }

    public void saveAndContinue() {
        try {
            commentModel.save();
            commentModel.prepareForCreate();
            Util.reloadBindings(editWindow);
            notificator.info("Comment added");
        } catch (DuplicateName e) {
            notificator.error("Duplicated Comment");
        }
    }

    public void openEditForm(Comment comment){
				try {
						commentModel.prepareForEdit(comment.getId());
						showEditWindow(String.format("Edit Model: %s", comment));
				} catch (InstanceNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
    }

    private void setTitle(String title) {
        Tab tab = (Tab) editWindow.getFellowIfAny("tab");
        if (tab != null) {
            tab.setLabel(title);
        }
    }

    private OnlyOneVisible getVisibility() {
        if (visibility == null) {
            visibility = new OnlyOneVisible(listWindow, editWindow);
        }
        return visibility;
    }

    private void showEditWindow(String title) {
        setTitle(title);
        Util.reloadBindings(editWindow);
        getVisibility().showOnly(editWindow);
    }
}
