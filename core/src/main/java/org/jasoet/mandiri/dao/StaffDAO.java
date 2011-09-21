package org.jasoet.mandiri.dao;

import org.jasoet.mandiri.domain.Staff;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

/**
 * Author : Deny Prasetyo S.T.
 * E-mail : jasoet87@gmail.com
 */

public interface StaffDAO extends GenericDAO<Staff> {
    /* -------------------------- Other Methods -------------------------- */
    @Transactional(readOnly = true)
    public Staff findByUsername(String username) throws DataAccessException, EntityNotFoundException;
}
