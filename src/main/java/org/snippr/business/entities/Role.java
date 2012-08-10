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

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

/**
 *
 * @author Antonio Arias <antonio.arias@gmail.com>
 *
 */
public class Role extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -3351912265451405463L;

    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public GrantedAuthority getGrantedAuthority() {
        return new GrantedAuthorityImpl(roleName);
    }
}
