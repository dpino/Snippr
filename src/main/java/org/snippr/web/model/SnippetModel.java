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

import org.snippr.business.dao.ISnippetDAO;
import org.snippr.business.entities.Snippet;
import org.snippr.business.exceptions.DuplicateName;
import org.snippr.business.exceptions.InstanceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author José Manuel Ciges Regueiro <jmanuel@ciges.net>
 * @version 20120810
 */
@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SnippetModel implements ISnippetModel {

    @Autowired
    private ISnippetDAO snippetDAO;

    private Snippet snippet;

    @Override
    @Transactional(readOnly = true)
    public List<Snippet> getAll() {
        return (List<Snippet>) snippetDAO.getAll();
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

    }

    @Override
    @Transactional
    public void delete(Snippet snippet) throws InstanceNotFoundException {
        snippetDAO.remove(snippet.getId());
    }

}
