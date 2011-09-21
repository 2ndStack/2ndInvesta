package org.jasoet.mandiri.dao.impl;

/**
 * Author : Deny Prasetyo S.T.
 * E-mail : jasoet87@gmail.com
 */

import org.jasoet.mandiri.dao.GenericDAO;
import org.jasoet.mandiri.domain.DomainObject;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@SuppressWarnings("unchecked")
public class GenericDAOImpl<T extends DomainObject> implements GenericDAO<T> {
/*------------------------------ Fields ------------------------------*/

    protected EntityManager entityManager;

    private Class<T> type;

/* --------------------------- Constructor ---------------------------*/

    public GenericDAOImpl(Class<T> type) {
        super();
        this.type = type;
    }

/* --------------------- Getter and Setter ---------------------*/

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

/* --------------------- Interface GenericDAO --------------------- */

    @Override
    public long count() throws DataAccessException {
        return (Long) this.entityManager.createQuery("SELECT count(o) FROM " + type.getName() + " o").getSingleResult();
    }

    public void delete(T object) throws DataAccessException {
        entityManager.remove(object);
    }

    public T get(Long id) throws DataAccessException {
        entityManager.clear();
        return entityManager.find(type, id);
    }

    public List<T> getAll() throws DataAccessException {
        return entityManager.createQuery("select obj from " + type.getName() + " obj").getResultList();
    }

    @Override
    public List<T> getEntries(int first, int max) throws DataAccessException {
        return entityManager.createQuery("select obj from " + type.getName() + " obj").setFirstResult(first).setMaxResults(max).getResultList();
    }

    public void merge(T object) throws DataAccessException {
        entityManager.merge(object);
    }

    public void save(T object) throws DataAccessException {
        entityManager.persist(object);
    }
}
