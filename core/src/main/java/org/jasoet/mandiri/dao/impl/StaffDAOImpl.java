package org.jasoet.mandiri.dao.impl;

import org.jasoet.mandiri.dao.StaffDAO;
import org.jasoet.mandiri.domain.Staff;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

/**
 * Author : Deny Prasetyo S.T.
 * E-mail : jasoet87@gmail.com
 */
@Repository("staffDAOImpl")
public class StaffDAOImpl extends GenericDAOImpl<Staff> implements StaffDAO {
/* --------------------------- Constructor ---------------------------*/

    public StaffDAOImpl() {
        super(Staff.class);
    }

/* --------------------- Interface StaffDAO --------------------- */

 
    @Override
    public Staff findByUsername(String username) throws DataAccessException, EntityNotFoundException {
        return (Staff) this.entityManager.createQuery("SELECT o FROM Staff o WHERE o.username = :username AND o.enabled=:enabled").setParameter("enabled",true).setParameter("username", username).getSingleResult();
    }
}
