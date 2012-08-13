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
 * Class that represents one of the codes which implements the snippet.
 *
 * @author Jorge Muñoz Castañer
 * @version 20120804
 *
 */
public class SnippetCode extends BaseEntity {

    private String code;

    private Snippet snippet;

    /**
     * The string which represents the code.
     */
    public String getCode() {
        return code;
    }

    /**
     * {@link SnippetCode#getCode()}
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * The description of what the snippet does and other information the user regards interesting
     */
    public Snippet getSnippet() {
        return snippet;
    }

    /**
     * {@link SnippetCode#getSnippet()}
     */
    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }

    /**
     * Snippet constructor
     *
     * @param String
     *            $code {@link SnippetCode#getCode()}
     * @param Snippet
     *            $snippet {@link SnippetCode#getSnippet()}
     */
    public SnippetCode(String code, Snippet snippet) {
        this.setCode(code);
        this.setSnippet(snippet);
    }

    /**
     * Default snippet constructor with no arguments
     */
    public SnippetCode() {
    }

    @Override
    public String toString() {
        return "SnippetCode [code=" + code + ", snippet=" + snippet + "]";
    }

}
