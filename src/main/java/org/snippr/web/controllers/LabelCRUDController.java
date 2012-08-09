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

package org.snippr.web.controllers;

import java.io.IOException;
import java.util.List;

import org.snippr.business.entities.Label;
import org.snippr.business.exceptions.DuplicateName;
import org.snippr.business.exceptions.InstanceNotFoundException;
import org.snippr.web.common.Notificator;
import org.snippr.web.common.OnlyOneVisible;
import org.snippr.web.common.Util;
import org.snippr.web.model.ILabelModel;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Tab;
import org.zkoss.zul.api.Window;

public class LabelCRUDController extends GenericForwardComposer {

    private OnlyOneVisible visibility;

    private org.zkoss.zul.Label notificationMessage;

    private Window listWindow;

    private Window editWindow;

    private Notificator notificator;

    private ILabelModel labelModel;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this);
        notificator = Notificator.create(notificationMessage);
    }

    public List<Label> getLabels() {
        return labelModel.getAll();
    }

    public Label getLabel() {
        return labelModel.getLabel();
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

    public void openEditForm(Label label) {
        try {
            labelModel.prepareForEdit(label.getId());
            showEditWindow(String.format("Edit Label: %s", label.getName()));
        } catch (InstanceNotFoundException e) {
            e.printStackTrace();
        }
        showEditWindow(String.format("Edit Label: %s", label.getName()));
    }

    public void delete(Label label) throws InstanceNotFoundException {
        labelModel.delete(label);
        Util.reloadBindings(listWindow);
    }

    public void openCreateForm() {
        labelModel.prepareForCreate();
        showEditWindow("New Label");
    }

    public void cancel() throws IOException {
        Executions.sendRedirect("/labels/labels.zul");
    }

    public void saveAndExit() throws IOException {
        try {
            labelModel.save();
            cancel();
        } catch (DuplicateName e) {
            notificator.error("Duplicated Label");
        }
    }

    public void saveAndContinue() {
        try {
            labelModel.save();
            labelModel.prepareForCreate();
            Util.reloadBindings(editWindow);
            notificator.info("Label added");
        } catch (DuplicateName e) {
            notificator.error("Duplicated Label");
        }
    }
}