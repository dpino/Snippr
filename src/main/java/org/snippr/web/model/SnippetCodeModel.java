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
import org.snippr.business.dao.ISnippetCodeDAO;
import org.snippr.business.dao.ISnippetDAO;
import org.snippr.business.entities.Snippet;
import org.snippr.business.entities.SnippetCode;
import org.snippr.business.exceptions.DuplicateName;
import org.snippr.business.exceptions.InstanceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class that implements the snippet code web model.
 *
 * @author Jorge Muñoz Castañer
 * @version 20120804
 *
 */
@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SnippetCodeModel implements ISnippetCodeModel {

    @Autowired
    private ISnippetCodeDAO snippetCodeDAO;

    @Autowired
    private ISnippetDAO snippetDAO;

    private SnippetCode snippetCode;

    @Override
    @Transactional(readOnly = true)
    public List<SnippetCode> getAll() {
        List<SnippetCode> snippetCodes = snippetCodeDAO.getAll();
        initializeSnippetCodes(snippetCodes);
        return snippetCodes;
    }

    private void initializeSnippetCodes(List<SnippetCode> snippetCodes) {
        for (SnippetCode each : snippetCodes) {
            Hibernate.initialize(each.getSnippet());
        }
    }

    @Override
    public void setSnippetCode(SnippetCode snippetCode) {
        this.snippetCode = snippetCode;
    }

    @Override
    public SnippetCode getSnippetCode() {
        return snippetCode;
    }

    @Override
    @Transactional
    public void save() throws DuplicateName {
        if (snippetCode.getId() == null && snippetCodeDAO.exists(snippetCode)) {
            throw new DuplicateName();
        }
        if (!snippetCode.getCode().isEmpty()) {
            snippetCodeDAO.save(snippetCode);
        }
    }

    @Override
    public void prepareForCreate() {
        snippetCode = new SnippetCode();
    }

    @Override
    @Transactional(readOnly = true)
    public void prepareForEdit(Long id) throws InstanceNotFoundException {
        snippetCode = snippetCodeDAO.find(id);
    }

    @Override
    @Transactional
    public void delete(SnippetCode snippetCode)
            throws InstanceNotFoundException {
        snippetCodeDAO.remove(snippetCode.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Snippet> getSnippets() {
        return snippetDAO.getAll();
    }

}
