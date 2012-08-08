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

import org.snippr.business.entities.Label;
import org.snippr.business.exceptions.DuplicateName;
import org.snippr.business.exceptions.InstanceNotFoundException;
import org.snippr.web.common.LabelCatalogSingleton;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class LabelModel implements ILabelModel {

    private Label label;

    @Override
    public List<Label> getAll() {
        return (List<Label>) LabelCatalogSingleton.instanceOf().getLabels();
    }

    @Override
    public Label getLabel() {
        return label;
    }

    @Override
    public void delete(Label label) throws InstanceNotFoundException {
        LabelCatalogSingleton.instanceOf().removeLabel(label.getId());
    }

    @Override
    public void save() throws DuplicateName {
        LabelCatalogSingleton.instanceOf().insertLabel(label);
    }

    @Override
    public void prepareForCreate() {
        label = new Label();
    }

    @Override
    public void prepareForEdit(Long id) throws InstanceNotFoundException {
        label = LabelCatalogSingleton.instanceOf().getLabel(id);
    }

    @Override
    public void setLabel(Label label) {
        this.label = label;
    }
}