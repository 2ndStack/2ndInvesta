package org.jasoet.mandiri.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Author : Deny Prasetyo S.T.
 * E-mail : jasoet87@gmail.com
 */

@Entity
@Table (name = "role")
public class Role implements GrantedAuthority, DomainObject {
/*------------------------------ Fields ------------------------------*/

    @Id
    @Column(length = 20)
    @Size(max = 20, min = 1)
    private String authority;

/* --------------------------- Constructor ---------------------------*/

    public Role() {
    }

    public Role(String authority) {
        this.authority = authority;
    }

/* --------------------- Getter and Setter ---------------------*/

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

/* ------------------------ Canonical Methods ------------------------*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (authority != null ? !authority.equals(role.authority) : role.authority != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return authority != null ? authority.hashCode() : 0;
    }

/* -------------------------- Other Methods -------------------------- */

    public int compareTo(Object o) {
        return authority.compareTo((String) o);
    }
}
