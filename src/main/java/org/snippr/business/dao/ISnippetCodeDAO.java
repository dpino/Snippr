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

import org.snippr.business.entities.SnippetCode;

/**
 * Interface for the DAO class of SnippetCode
 *
 * @author Jorge Muñoz Castañer
 * @version 20120811
 *
 */
public interface ISnippetCodeDAO extends IGenericDAO<SnippetCode, Long> {

    /**
     * Returns the list of snippet codes from the database
     *
     * @return List<SnippetCode>
     */
    List<SnippetCode> getAll();

    /**
     * Verifies that the snippet code exists in the database
     *
     * @param snippetCode
     *            SnippetCode instance
     * @return
     */
    boolean exists(SnippetCode snippetCode);

}
