package org.jasoet.mandiri.dao;

import org.jasoet.mandiri.domain.Role;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author : Deny Prasetyo S.T.
 * E-mail : jasoet87@gmail.com
 */


public interface RoleDAO {
    /* -------------------------- Other Methods -------------------------- */
    @Transactional(readOnly = true)
    public long count() throws DataAccessException;

    public void delete(Role object) throws DataAccessException;

    @Transactional(readOnly = true)
    public Role get(String authority) throws DataAccessException;

    @Transactional(readOnly = true)
    public List<Role> getAll() throws DataAccessException;

    @Transactional(readOnly = true)
    public List<Role> getEntries(int first, int max) throws DataAccessException;

    @Transactional
    public void merge(Role object) throws DataAccessException;

    @Transactional
    public void save(Role object) throws DataAccessException;
}
