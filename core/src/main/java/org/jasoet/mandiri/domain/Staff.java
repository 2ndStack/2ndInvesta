package org.jasoet.mandiri.domain;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Author : Deny Prasetyo S.T.
 * E-mail : jasoet87@gmail.com
 */
@Entity
@Table(name = "staff", uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "enabled"})})
@Cacheable(value = false)
public class Staff implements UserDetails, Serializable, DomainObject {
/*------------------------------ Fields ------------------------------*/

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 20)
    @Size(max = 20, min = 1)
    private String username;

    @Column(nullable = false, length = 100)
    @Size(max = 100, min = 1)
    private String password;

    @Column(nullable = false)
    private boolean enabled;

    @Column(length = 20)
    private String telephone;

    @Column(length = 41)
    private String salt;

    @Transient
    private boolean accountNonExpired = true;

    @Transient
    private boolean accountNonLocked = true;

    @Transient
    private boolean credentialsNonExpired = true;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(name = "staff_role", joinColumns = {@JoinColumn(name = "staff_id")}, inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<Role>();

/* --------------------- Getter and Setter ---------------------*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

/* --------------------- Interface UserDetails --------------------- */

    @Override
    public Collection getAuthorities() {
        return roles;
    }

/* -------------------------- Other Methods -------------------------- */

    public boolean isRoleEnabled(String authority) {
        Role checked = new Role(authority);

        return getRoles().contains(checked);
    }
}
