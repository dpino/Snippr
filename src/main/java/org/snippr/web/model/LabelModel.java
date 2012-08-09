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

import org.snippr.business.dao.ILabelDAO;
import org.snippr.business.entities.Label;
import org.snippr.business.exceptions.DuplicateName;
import org.snippr.business.exceptions.InstanceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class LabelModel implements ILabelModel {

    @Autowired
    private ILabelDAO labelDAO;

    private Label label;

    @Override
    @Transactional
    public List<Label> getAll() {
        return labelDAO.getAll();
    }

    @Override
    public Label getLabel() {
        return label;
    }

    @Override
    @Transactional
    public void delete(Label label) throws InstanceNotFoundException {
        labelDAO.remove(label.getId());
    }

    @Override
    @Transactional
    public void save() throws DuplicateName {
        if (label.getId() == null && labelDAO.exists(label)) {
            throw new DuplicateName();
        }
        if (!label.getName().isEmpty()) {
            labelDAO.save(label);
        }
    }

    @Override
    public void prepareForCreate() {
        label = new Label();
    }

    @Override
    @Transactional
    public void prepareForEdit(Long id) throws InstanceNotFoundException {
        label = labelDAO.find(id);
    }

    @Override
    public void setLabel(Label label) {
        this.label = label;
    }
}