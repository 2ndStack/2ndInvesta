package org.jasoet.mandiri.dao.impl;

import org.jasoet.mandiri.dao.EmailDAO;
import org.jasoet.mandiri.domain.Email;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

@Repository("emailDAOImpl")
public class EmailDAOImpl extends GenericDAOImpl<Email> implements EmailDAO {
/* --------------------------- Constructor ---------------------------*/

    public EmailDAOImpl() {
        super(Email.class);
    }

    public Email getFirstPriorityEmail() throws DataAccessException {
        this.entityManager.clear();
        Query query = this.entityManager.createNativeQuery("SELECT " +
                "email.id, " +
                "email.created, " +
                "email.delivered, " +
                "email.priority, " +
                "email.subj, " +
                "email.txt, " +
                "email.destination " +
                "FROM " +
                "email email " +
                "WHERE " +
                "email.delivered = false " +
                "ORDER BY email.priority ASC, email.created ASC " +
                "LIMIT 0,1", Email.class);
        try {
            return (Email) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }


}
