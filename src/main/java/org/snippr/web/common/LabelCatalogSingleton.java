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

package org.snippr.web.common;

import java.util.ArrayList;
import java.util.Collection;

import org.snippr.business.entities.Label;

public class LabelCatalogSingleton {

    private static LabelCatalogSingleton instance;

    private final Collection<Label> cLabels;

    private long nextId;

    private LabelCatalogSingleton() {
        this.cLabels = new ArrayList<Label>();
        this.nextId = 0;
        loadCatalog();
    }

    public static LabelCatalogSingleton instanceOf() {
        if (instance == null) {
            instance = new LabelCatalogSingleton();
        }
        return instance;
    }

    public Collection<Label> getLabels() {
        return cLabels;
    }

    private void loadCatalog() {
        Label labelGit = new Label("git");
        labelGit.setId(nextId++);
        Label labelJava = new Label("java");
        labelJava.setId(nextId++);
        Label labelZk = new Label("zk");
        labelZk.setId(nextId++);
        Label labelHibernate = new Label("hibernate");
        labelHibernate.setId(nextId++);

        cLabels.add(labelGit);
        cLabels.add(labelJava);
        cLabels.add(labelZk);
        cLabels.add(labelHibernate);
    }

    public void insertLabel(Label label) {
        if (label.getId() == null) {
            label.setId(nextId++);
            cLabels.add(label);
        }
    }

    public void removeLabel(Long id) {
        for (Label label : cLabels) {
            if (label.getId() == id) {
                cLabels.remove(label);
                break;
            }
        }
    }

    public Label getLabel(Long labelId) {
        Label returnLabel = null;
        for (Label label : cLabels) {
            if (label.getId() == labelId) {
                returnLabel = label;
                break;
            }
        }
        return returnLabel;
    }
}