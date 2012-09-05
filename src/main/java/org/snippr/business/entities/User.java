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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Antonio Arias <antonio.arias@gmail.com>
 * @author José Manuel Ciges Regueiro <jmanuel@ciges.net>
 * @version 20120901
 */
public class User extends BaseEntity implements UserDetails {
    private static final long serialVersionUID = -7594388337646930762L;

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean accountNonLocked;
    private final Set<Role> roles = new HashSet<Role>(0);
    private Collection<GrantedAuthority> authorities;
    private Set <Snippet> snippets;
    private Set <Label> labels;
    private Set <Comment> comments;

    public User invalidate() {
        enabled = true;
        accountNonExpired = true;
        credentialsNonExpired = false;
        accountNonLocked = true;

        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        this.authorities = new ArrayList<GrantedAuthority>();
        for (Role role : roles) {
            this.authorities.add(role.getGrantedAuthority());
        }
        return authorities;
    }

    /**
     * Gets the snippets for this user
     */
	public Set<Snippet> getSnippets() {
		return Collections.unmodifiableSet(this.snippets);
	}

	/**
     * Add a snippet to the collections of snippets for this user
     */
    public void addSnippet(Snippet snippet) {
        snippet.setUser(this);
        snippets.add(snippet);
    }

    /**
     * Remove a snippet from the collection of snippets for this user
     */
    public void removeSnippet(Snippet snippet)  {
        snippet.setUser(null);
        snippets.remove(snippet);
    }

    /**
     * Gets the labels for this user
     */
    public Set<Label> getLabels()   {
        return Collections.unmodifiableSet(this.labels);
    }

    /**
     * Add a label to the collections of labels for this user
     */
    public void addLabel(Label label) {
        label.setUser(this);
        labels.add(label);
    }

    /**
     * Remove a label from the collection of labels for this user
     */
    public void removeLabel(Label label)  {
        label.setUser(null);
        labels.remove(label);
    }

	public Set <Comment> getComments() {
		return comments;
	}

	public void setComments(Set <Comment> comments) {
		this.comments = comments;
	}

}
