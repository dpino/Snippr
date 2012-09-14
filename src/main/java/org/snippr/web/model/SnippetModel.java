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
package org.snippr.web.model;

import java.util.List;

import org.hibernate.Hibernate;
import org.snippr.business.dao.ILabelDAO;
import org.snippr.business.dao.ISnippetCodeDAO;
import org.snippr.business.dao.ISnippetDAO;
import org.snippr.business.dao.IUserDAO;
import org.snippr.business.entities.Label;
import org.snippr.business.entities.Snippet;
import org.snippr.business.entities.SnippetCode;
import org.snippr.business.entities.User;
import org.snippr.business.exceptions.DuplicateName;
import org.snippr.business.exceptions.InstanceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author José Manuel Ciges Regueiro <jmanuel@ciges.net>
 * @author Jorge Muñoz Castañer <punkto@gmail.com>
 * @version 20120817
 */
@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SnippetModel implements ISnippetModel {

    @Autowired
    private ISnippetDAO snippetDAO;

    @Autowired
    private ISnippetCodeDAO snippetCodeDAO;

    @Autowired
    private ILabelDAO labelDAO;

    @Autowired
    private IUserDAO userDAO;

    private Snippet snippet;

    private List<Snippet> snippets;

    private List<Label> labels;

    @Override
    @Transactional(readOnly = true)
    public List<Snippet> getAll() {
        List<Snippet> snippets = snippetDAO.getAll();
        for (Snippet each:snippets) {
            initializeSnippet(each);
        }
        return snippets;
    }

    @Override
    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }

    @Override
    public Snippet getSnippet() {
        return this.snippet;
    }

    @Override
    @Transactional
    public void save() throws DuplicateName {
        if (snippet.getId() == null && snippetDAO.exists(snippet)) {
            throw new DuplicateName();
        }
        // Both title and description are required
        if (!snippet.getTitle().isEmpty()
                && !snippet.getDescription().isEmpty()) {
            snippetDAO.save(snippet);
        }
    }

    @Override
    public void prepareForCreate() {
        snippet = new Snippet();
    }

    @Override
    @Transactional(readOnly = true)
    public void prepareForEdit(Long id) throws InstanceNotFoundException {
        snippet = snippetDAO.find(id);
        initializeSnippet(snippet);
    }

    @Override
    @Transactional
    public void delete(Snippet snippet) throws InstanceNotFoundException {
        snippetDAO.remove(snippet.getId());
    }

    /**
     * Part N of 1-N relationship between Snippet and SnippetCode is loaded "lazely"
     * In this helper function we use a proxy in Hibernate to force load
     *
     * @param snippet
     */
    private void initializeSnippet(Snippet snippet) {
        Hibernate.initialize(snippet.getSnippetCodes());
        for (SnippetCode each:snippet.getSnippetCodes())    {
            Hibernate.initialize(each);
        }
    }

    /**
     * Get the codes for a snippet?
     */
    @Override
    @Transactional(readOnly = true)
    public List<SnippetCode> getSnippetCodes()  {
        return snippetCodeDAO.getAll();
    }

    /**
     * Create a new SnippetCode for this snippet
     */
    @Transactional
    public void addNewSnippetCode()  {
        SnippetCode snippetCode = new SnippetCode();
        snippetCode.setCode("Introduce your code here ...");
        snippet.addSnippetCode(snippetCode);
    }

    /**
     * Delete a SnippetCode for this snippet
     */
    @Transactional
    public void deleteSnippetCode(SnippetCode snippetCode) throws InstanceNotFoundException {
        snippet.removeSnippetCode(snippetCode);
        snippetCodeDAO.remove(snippetCode.getId());
    }

    /**
     * Create a new Snippet with default data.
     *
     * @throws DuplicateName
     */
    @Override
    @Transactional
    public void addNewSnippet(Label label) throws DuplicateName {
        User user = userDAO.getCurrentUser();
        snippet = new Snippet();
        snippet.setTitle("New Snippet, please fill it.");
        snippet.setDescription("Add a description here.");
        snippet.setUser(user);
        snippet.setLabel(label);
        if (snippetDAO.exists(snippet)) {
            throw new DuplicateName();
        }
        snippetDAO.save(snippet);
    }

    @Override
    @Transactional
    public void addNewLabel() throws DuplicateName {
        User user = userDAO.getCurrentUser();
        Label label = new Label();
        label.setName("<No name>");
        label.setUser(user);
        if (labelDAO.exists(label)) {
            throw new DuplicateName();
        }
        labelDAO.save(label);
    }

    /**
     * Updates a Snippet with new data (equal id).
     *
     * @throws InstanceNotFoundException
     *
     * @throws DuplicateName
     */
    @Override
    public void updateSnippet(Snippet snippet)
            throws InstanceNotFoundException, DuplicateName {
        snippetDAO.save(snippet);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Snippet> getSnippetsByLabel(Label label) {
        User user = userDAO.getCurrentUser();

        if (label == null) {
            labels = getLabels();
            if (!labels.isEmpty()) {
                label = labels.get(0);
            }
        }

        if (snippets == null) {
            snippets = label != null ? snippetDAO.getAllByUser(user) :
                snippetDAO.getAllByUserAndLabel(user, label);
        }
        return snippets;
    }

    private List<Label> getLabels() {
        User user = userDAO.getCurrentUser();
        if (labels == null) {
            labels = labelDAO.getAllByUser(user);
        }
        return labels;
    }

}