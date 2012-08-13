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
import java.util.ArrayList;
import java.util.List;

import org.snippr.business.entities.Snippet;
import org.snippr.business.entities.SnippetCode;
import org.snippr.business.exceptions.DuplicateName;
import org.snippr.business.exceptions.InstanceNotFoundException;
import org.snippr.web.common.Notificator;
import org.snippr.web.common.OnlyOneVisible;
import org.snippr.web.common.Util;
import org.snippr.web.model.ISnippetCodeModel;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Tab;
import org.zkoss.zul.api.Window;

/**
 * Class that implements the snippet code CRUD controller.
 *
 * @author Jorge Muñoz Castañer
 * @version 20120804
 *
 */
public class SnippetCodeCRUDController extends GenericForwardComposer {

    private OnlyOneVisible visibility;

    private Label notificationMessage;

    private Window listWindow;

    private Window editWindow;

    private ISnippetCodeModel snippetCodeModel;

    private Notificator notificator;

    private SnippetRenderer snippetRenderer = new SnippetRenderer();


    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this);
        notificator = Notificator.create(notificationMessage);
    }

    public void selectSnippet(SelectEvent event) {
        SnippetCode snippetCode = snippetCodeModel.getSnippetCode();

        List<Listitem> items = new ArrayList(event.getSelectedItems());
        if (!items.isEmpty()) {
            Listitem item = items.get(0);
            snippetCode.setSnippet((Snippet) item.getValue());
        }
    }

    public List<SnippetCode> getSnippetCodes() {
        return snippetCodeModel.getAll();
    }


    public void openCreateForm() {
        snippetCodeModel.prepareForCreate();
        showEditWindow("New snippetCode");
    }

    private void showEditWindow(String title) {
        setTitle(title);
        Util.reloadBindings(editWindow);
        getVisibility().showOnly(editWindow);
    }

    private void setTitle(String title) {
        Tab tab = (Tab) editWindow.getFellowIfAny("tab");
        if (tab != null) {
            tab.setLabel(title);
        }
    }

    public void saveAndExit() throws IOException {
        try {
            snippetCodeModel.save();
            cancel();
        } catch (DuplicateName e) {
            notificator.error("Duplicated snippetCode");
        }
    }

    public void saveAndContinue() {
        try {
            snippetCodeModel.save();
            snippetCodeModel.prepareForCreate();
            Util.reloadBindings(editWindow);
            notificator.info("Snippet code added");
        } catch (DuplicateName e) {
            notificator.error("Duplicated snippetCode");
        }
    }

    public void cancel() throws IOException {
        Executions.sendRedirect("/snippetcodes/snippetcodes.zul");
    }

    public SnippetCode getSnippetCode() {
        return snippetCodeModel.getSnippetCode();
    }

    private OnlyOneVisible getVisibility() {
        if (visibility == null) {
            visibility = new OnlyOneVisible(listWindow, editWindow);
        }
        return visibility;
    }

    public void openEditForm(SnippetCode snippetCode) {
        try {
            snippetCodeModel.prepareForEdit(snippetCode.getId());
            showEditWindow(String.format("Edit snippetCode"));
        } catch (InstanceNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void delete(SnippetCode snippetCode)
            throws InstanceNotFoundException {
        snippetCodeModel.delete(snippetCode);
        Util.reloadBindings(listWindow);
    }

    public List<Snippet> getSnippets() {
        return snippetCodeModel.getSnippets();
    }

    public SnippetRenderer getSnippetRenderer() {
        return snippetRenderer;
    }

    private class SnippetRenderer implements ListitemRenderer {

        @Override
        public void render(Listitem item, Object data) throws Exception {
            Snippet snippet = (Snippet) data;

            item.setValue(data);
            item.appendChild(new Listcell(data.toString()));

            // Select snippetCode.snippet in list
            SnippetCode snippetCode = snippetCodeModel.getSnippetCode();
            if (snippetCode != null && snippetCode.getSnippet() != null
                    && snippetCode.getSnippet().getId().equals(snippet.getId())) {
                item.setSelected(true);
            }
        }

    }

}
