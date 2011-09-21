package org.jasoet.mandiri.dao.impl;

import org.jasoet.mandiri.dao.TestimoniDAO;
import org.jasoet.mandiri.domain.Testimoni;
import org.springframework.stereotype.Repository;

@Repository("testimoniDAOImpl")
public class TestimoniDAOImpl extends GenericDAOImpl<Testimoni> implements TestimoniDAO {
/* --------------------------- Constructor ---------------------------*/

    public TestimoniDAOImpl() {
        super(Testimoni.class);
    }
}
