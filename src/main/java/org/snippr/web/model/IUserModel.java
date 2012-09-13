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

import java.util.Set;

import org.snippr.business.entities.Snippet;
import org.snippr.business.entities.User;
import org.snippr.business.entities.Label;
import org.snippr.business.exceptions.InstanceNotFoundException;

/**
 * @author José Manuel Ciges Regueiro <jmanuel@ciges.net>
 * @version 20120911
 */
public interface IUserModel {

    void prepareForEdit(String username) throws InstanceNotFoundException;

    void setUser(User user);

    User getUser();

    Set<Label> getLabels();

    void addNewLabel();

    void deleteLabel(Label label) throws InstanceNotFoundException ;

    Set<Snippet> getSnippets();

    void addNewSnippet();

    void deleteSnippet(Snippet snippet) throws InstanceNotFoundException;

    public Set<Snippet> getSnippetsWithLabel(String labelname);

}
