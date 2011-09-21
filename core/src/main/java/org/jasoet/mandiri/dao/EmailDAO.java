package org.jasoet.mandiri.dao;

import org.jasoet.mandiri.domain.Email;
import org.springframework.dao.DataAccessException;

public interface EmailDAO extends GenericDAO<Email> {
    public Email getFirstPriorityEmail() throws DataAccessException;
}
