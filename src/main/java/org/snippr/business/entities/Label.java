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

package org.snippr.business.entities;

import java.util.Collections;
import java.util.Set;

/**
 * @version 20120830
 */
public class Label extends BaseEntity {

    private String name;

    private User user;

    private Set<Snippet> snippets;


    public Label() {
    }

    public Label(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the user which owns this label
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user which owns this label
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the snippets with this label
     */
    public Set<Snippet> getSnippets()   {
        return Collections.unmodifiableSet(this.snippets);
    }

    /**
     * Add a snippet to the collections of snippets with this label
     */
    public void addSnippet(Snippet snippet) {
        snippet.setLabel(this);
        snippets.add(snippet);
    }

    /**
     * Remove a snippet from the collection of snippets with this label
     */
    public void removeSnippet(Snippet snippet)  {
        snippet.setLabel(null);
        snippets.remove(snippet);
    }

}
