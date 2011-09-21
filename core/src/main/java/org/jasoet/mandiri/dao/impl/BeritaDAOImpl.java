package org.jasoet.mandiri.dao.impl;

import org.jasoet.mandiri.dao.BeritaDAO;
import org.jasoet.mandiri.domain.Berita;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author : Deny Prasetyo S.T.
 * E-mail : jasoet87@gmail.com
 */
@Repository("beritaDAOImpl")
public class BeritaDAOImpl extends GenericDAOImpl<Berita> implements BeritaDAO {
/* --------------------------- Constructor ---------------------------*/

    public BeritaDAOImpl() {
        super(Berita.class);
    }

/* --------------------- Interface BeritaDAO --------------------- */

    @Override
    public List<Berita> getEntriesOrderByTanggalDesc(int first, int max) throws DataAccessException {
        this.entityManager.clear();
        return entityManager.createQuery("select obj from Berita obj ORDER BY id DESC, tanggal DESC ").setFirstResult(first).setMaxResults(max).getResultList();
    }

    @Override
    public List<Berita> getAllOrderByTanggalDesc() throws DataAccessException {
        this.entityManager.clear();
        return entityManager.createQuery("select obj from Berita obj ORDER BY tanggal DESC ").getResultList();
    }
}
