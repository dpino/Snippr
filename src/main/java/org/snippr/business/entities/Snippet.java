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

/**
 * Class that represents a Snippet of code
 *
 * @author José Manuel Ciges Regueiro
 * @version 20120803
 *
 */
public class Snippet extends BaseEntity {

    private String title;
    private String description;

    /**
     * The string which identifies the snippet, what it does.
     */
    public String getTitle() {
        return title;
    }
    /**
     * {@link Snippet#getTitle()}
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * The description of what the snippet does and other information the user regards interesting
     */
    public String getDescription() {
        return description;
    }
    /**
     * {@link Snippet#getDescription()}
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Snippet constructor
     * @param String $title         {@link Snippet#getTitle()}
     * @param String $description    {@link Snippet#getDescription()}
     */
    public Snippet(String title, String description) {
        this.setTitle(title);
        this.setDescription(description);
    }

    @Override
    public String toString() {
        return "Snippet [title=" + title + ", description=" + description + "]";
    }

}
