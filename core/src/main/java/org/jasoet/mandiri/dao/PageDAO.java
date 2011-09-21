package org.jasoet.mandiri.dao;

import org.jasoet.mandiri.domain.Page;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PageDAO extends GenericDAO<Page> {
    /* -------------------------- Other Methods -------------------------- */
    @Transactional(readOnly = true)
    public List<Page> getEntriesOrderByTanggalDesc(int first, int max) throws DataAccessException;

    @Transactional(readOnly = true)
    public List<Page> getAllOrderByTanggalDesc() throws DataAccessException;
}
