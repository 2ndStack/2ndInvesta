package org.jasoet.mandiri.dao.impl;

import org.jasoet.mandiri.dao.BankDAO;
import org.jasoet.mandiri.domain.Bank;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

@Repository("bankDAOImpl")
public class BankDAOImpl extends GenericDAOImpl<Bank> implements BankDAO {
/* --------------------------- Constructor ---------------------------*/

    public BankDAOImpl() {
        super(Bank.class);
    }

    @Override
    public Bank findByNama(String nama) throws DataAccessException,EntityNotFoundException {
        return (Bank) this.entityManager.createQuery("SELECT o FROM Bank o WHERE lower(o.nama)=:nama").setParameter("nama", nama.toLowerCase()).getSingleResult();
    }
}
