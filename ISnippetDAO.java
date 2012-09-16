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
package org.snippr.business.dao;

import java.util.List;

import org.snippr.business.entities.Label;
import org.snippr.business.entities.Snippet;
import org.snippr.business.entities.User;

/**
 * Interface for the DAO class of Snippet
 *
 * @author José Manuel Ciges Regueiro
 * @version 20120810
 *
 */
public interface ISnippetDAO extends IGenericDAO<Snippet, Long> {

    /**
     * Verifies that the snippet exists in the database
     * @param snippet   Snippet instance
     * @return
     */
    boolean exists(Snippet snippet);

    List<Snippet> getAllByUser(User user);

    List<Snippet> getAllByUserAndLabel(User user, Label label);

    List<Snippet> getAllByLabel(Label label);

}