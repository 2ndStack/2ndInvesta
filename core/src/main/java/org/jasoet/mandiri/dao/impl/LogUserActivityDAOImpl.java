package org.jasoet.mandiri.dao.impl;

import org.jasoet.mandiri.dao.LogUserActivityDAO;
import org.jasoet.mandiri.domain.LogUserActivity;
import org.springframework.stereotype.Repository;

@Repository("logUserActivityDAOImpl")
public class LogUserActivityDAOImpl extends GenericDAOImpl<LogUserActivity> implements LogUserActivityDAO {
/* --------------------------- Constructor ---------------------------*/

    public LogUserActivityDAOImpl() {
        super(LogUserActivity.class);
    }
}
