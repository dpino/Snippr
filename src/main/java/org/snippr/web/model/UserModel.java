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

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Hibernate;
import org.snippr.business.dao.ILabelDAO;
import org.snippr.business.dao.ISnippetDAO;
import org.snippr.business.dao.IUserDAO;
import org.snippr.business.entities.Label;
import org.snippr.business.entities.Snippet;
import org.snippr.business.entities.User;
import org.snippr.business.exceptions.InstanceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author José Manuel Ciges Regueiro <jmanuel@ciges.net>
 * @version 20120911
 */
@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class UserModel implements IUserModel {

    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private ILabelDAO labelDAO;

    @Autowired
    private ISnippetDAO snippetDAO;

    private User user;

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public User getUser() {
        return this.user;
    }

    /**
     *  Get the labels of this user
     *  @return Set<Label>
     *  @access public
     */
    @Override
    @Transactional(readOnly = true)
    public Set<Label> getLabels() {
        return user.getLabels();
    }

    /**
     * Create a new Label for this user
     * @access public
     */
    @Override
    public void addNewLabel() {
        Label label = new Label();
        user.addLabel(label);
    }

    /**
     * Delete a Label for this user
     * @param Label label
     * @access public
     */
    @Override
    public void deleteLabel(Label label) throws InstanceNotFoundException {
        user.removeLabel(label);
        labelDAO.remove(label.getId());
    }

    /**
     * Part N of 1-N relationships between User and Label, and User and Snippets are loaded "lazely"
     * In this helper function we use a proxy in Hibernate to force load them
     * @param User user
     */
    private void initializeUser(User user)  {
        Hibernate.initialize(user.getLabels());
        for (Label each:user.getLabels()){
            Hibernate.initialize(each);
        }
        Hibernate.initialize(user.getSnippets());
        for (Snippet each:user.getSnippets())   {
            Hibernate.initialize(each);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void prepareForEdit(String username) throws InstanceNotFoundException {
        user = userDAO.getByUsername(username);
        initializeUser(user);
    }

    /**
     *  Get all the snippets of this user
     *  @return Set<Snippet>
     */
    @Override
    @Transactional(readOnly = true)
    public Set<Snippet> getSnippets() {
        return user.getSnippets();
    }

    /**
     *  Get the snippet of this users with the label passed as parameter
     *  @param String labelname
     *  @return Set<Snippet>
     */
    @Transactional(readOnly = true)
    public Set<Snippet> getSnippetsWithLabel(String labelname){
        Set<Snippet> snippets = this.getSnippets();

        Label label = labelDAO.getByLabelname(labelname);
        long labelid = label.getId();

        Set<Snippet> results = new HashSet<Snippet>();
        for (Snippet each:snippets) {
            if (each.getId() == labelid)    {
                results.add(each);
            }
        }
        return results;
    }

    /**
     * Create a new Snippet for this user
     * @access public
     */
    @Override
    public void addNewSnippet() {
        Snippet snippet = new Snippet();
        user.addSnippet(snippet);
    }

    /**
     * Delete a snippet for this user
     * @param Snippet snippet
     * @access public
     */
    @Override
    public void deleteSnippet(Snippet snippet) throws InstanceNotFoundException {
        user.removeSnippet(snippet);
        snippetDAO.remove(snippet.getId());
    }
}
