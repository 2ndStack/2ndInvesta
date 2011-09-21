package org.jasoet.mandiri.dao.impl;

import org.jasoet.mandiri.dao.PageDAO;
import org.jasoet.mandiri.domain.Page;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author : Deny Prasetyo S.T.
 * E-mail : jasoet87@gmail.com
 */
@Repository("pageDAOImpl")
public class PageDAOImpl extends GenericDAOImpl<Page> implements PageDAO {
/* --------------------------- Constructor ---------------------------*/

    public PageDAOImpl() {
        super(Page.class);
    }

/* --------------------- Interface PageDAO --------------------- */

    @Override
    public List<Page> getEntriesOrderByTanggalDesc(int first, int max) throws DataAccessException {
        return entityManager.createQuery("select obj from Page obj ORDER BY tanggal DESC ").setFirstResult(first).setMaxResults(max).getResultList();
    }

    @Override
    public List<Page> getAllOrderByTanggalDesc() throws DataAccessException {
        return entityManager.createQuery("select obj from Page obj ORDER BY tanggal DESC ").getResultList();
    }
}
