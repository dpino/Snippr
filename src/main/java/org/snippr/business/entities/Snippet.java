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

import java.util.Set;

/**
 * Class that represents a Snippet of code
 *
 * @author José Manuel Ciges Regueiro
 * @version 20120831
 *
 */
public class Snippet extends BaseEntity {

    private String title;

    private String description;

    private Set<SnippetCode> snippetCodes;

    private User user ;

    private Label label;

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

    /**
     * Snippet constructor with no arguments
     */
    public Snippet() {
    }

    /**
     * Set relation between the snippet and its code fragments
     */
    public void setSnippetCodes(Set<SnippetCode> snippetCodes)  {
        this.snippetCodes = snippetCodes;
    }

    /**
     * Gets the code fragments for this snippet
     */
    public Set<SnippetCode> getSnippetCodes()   {
        return this.snippetCodes;
    }

    /**
     * Add a code fragment to this snippet
     */
    public void addSnippetCode(SnippetCode snippetCode) {
        snippetCode.setSnippet(this);
        snippetCodes.add(snippetCode);
    }

    /**
     * Remove a code fragment from this snippet
     */
    public void removeSnippetCode(SnippetCode snippetCode)  {
        snippetCode.setSnippet(null);
        snippetCodes.remove(snippetCode);
    }

    @Override
    public String toString() {
        return "Snippet [title=" + title + ", description=" + description + "]";
    }

    /**
     * Gets the owner this snippet
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the owner of this snippet
     */
    public void setUser(User user) {
        this.user = user;
    }


    /**
     * Gets the label assigned to this snippet
     */
    public Label getLabel() {
        return label;
    }

    /**
     * Sets the label assigned to this snippet
     */
    public void setLabel(Label label) {
        this.label = label;
    }

}
