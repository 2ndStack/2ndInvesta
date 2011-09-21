package org.jasoet.mandiri.dao;

import org.jasoet.mandiri.domain.DomainObject;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface GenericDAO<T extends DomainObject> {
/* -------------------------- Other Methods -------------------------- */

    @Transactional(readOnly = true)
    public long count() throws DataAccessException;

    @Transactional
    public void delete(T object) throws DataAccessException;

    @Transactional(readOnly = true)
    public T get(Long id) throws DataAccessException;

    @Transactional(readOnly = true)
    public List<T> getAll() throws DataAccessException;

    @Transactional(readOnly = true)
    public List<T> getEntries(int first, int max) throws DataAccessException;

    @Transactional
    public void merge(T object) throws DataAccessException;

    @Transactional
    public void save(T object) throws DataAccessException;
//
//    public void indexEntity(T object);
//
//    public void indexAllItems();
}
