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

import org.snippr.business.entities.Comment;
import org.snippr.business.entities.Label;
import org.snippr.business.entities.Snippet;
import org.snippr.business.entities.SnippetCode;
import org.snippr.business.exceptions.DuplicateName;
import org.snippr.business.exceptions.InstanceNotFoundException;

/**
 * @author José Manuel Ciges Regueiro <jmanuel@ciges.net>
 * @author Jorge Muñoz Castañer <punkto@gmail.com>
 * @version 20120817
 */
public interface ISnippetModel {

    List<Snippet> getAll();

    Snippet getSnippet();

    void prepareForCreate();

    void prepareForEdit(Long id) throws InstanceNotFoundException;

    void save() throws DuplicateName;

    void setSnippet(Snippet snippet);

    void delete(Snippet snippet) throws InstanceNotFoundException;

    List<SnippetCode> getSnippetCodes();

    void addNewSnippetCode();

    void deleteSnippetCode(SnippetCode snippetCode) throws InstanceNotFoundException;

    void addNewSnippet(Label label) throws DuplicateName;

    void updateSnippet(Snippet snippet) throws InstanceNotFoundException,
            DuplicateName;

    void addNewLabel() throws DuplicateName;

    List<Snippet> getSnippetsByLabel(Label label);

    List<Comment> getComments();

}