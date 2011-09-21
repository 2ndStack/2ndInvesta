package org.jasoet.mandiri.dao.impl;

import org.jasoet.mandiri.dao.RoleDAO;
import org.jasoet.mandiri.domain.Role;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

/**
 * Author : Deny Prasetyo S.T.
 * E-mail : jasoet87@gmail.com
 */
@Repository("roleDAOImpl")
@Transactional
public class RoleDAOImpl implements RoleDAO {
/*------------------------------ Fields ------------------------------*/

    protected EntityManager entityManager;

/* --------------------- Getter and Setter ---------------------*/

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

/* --------------------- Interface RoleDAO --------------------- */

    @Override
    public long count() throws DataAccessException {
        return (Long) this.entityManager.createQuery("SELECT count(o) FROM Role o").getSingleResult();
    }

    public void delete(Role object) throws DataAccessException {
        entityManager.remove(object);
    }

    public Role get(String id) throws DataAccessException {
        return entityManager.find(Role.class, id);
    }

    public List<Role> getAll() throws DataAccessException {
        return entityManager.createQuery("select obj from Role obj").getResultList();
    }

    @Override
    public List<Role> getEntries(int first, int max) throws DataAccessException {
        return entityManager.createQuery("select obj from Role obj").setFirstResult(first).setMaxResults(max).getResultList();
    }

    public void merge(Role object) throws DataAccessException {
        entityManager.merge(object);
    }

    public void save(Role object) throws DataAccessException {
        entityManager.persist(object);
    }
}
