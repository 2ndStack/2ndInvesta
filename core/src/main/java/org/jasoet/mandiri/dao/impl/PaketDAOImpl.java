package org.jasoet.mandiri.dao.impl;

import org.jasoet.mandiri.dao.PaketDAO;
import org.jasoet.mandiri.domain.Paket;
import org.springframework.stereotype.Repository;

@Repository("paketDAOImpl")
public class PaketDAOImpl extends GenericDAOImpl<Paket> implements PaketDAO {
/* --------------------------- Constructor ---------------------------*/

    public PaketDAOImpl() {
        super(Paket.class);
    }
}
