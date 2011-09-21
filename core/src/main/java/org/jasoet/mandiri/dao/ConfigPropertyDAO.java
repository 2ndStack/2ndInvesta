package org.jasoet.mandiri.dao;

import org.jasoet.mandiri.domain.ConfigProperty;
import org.springframework.dao.DataAccessException;

import javax.persistence.EntityNotFoundException;

public interface ConfigPropertyDAO extends GenericDAO<ConfigProperty> {
    public ConfigProperty findByKeyz(String keyz) throws DataAccessException,EntityNotFoundException;
}
