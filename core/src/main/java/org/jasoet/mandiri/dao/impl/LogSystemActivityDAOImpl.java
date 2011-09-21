package org.jasoet.mandiri.dao.impl;

import org.jasoet.mandiri.dao.LogSystemActivityDAO;
import org.jasoet.mandiri.domain.LogSystemActivity;
import org.springframework.stereotype.Repository;

@Repository("logAutomaticSystemActivityDAOImpl")
public class LogSystemActivityDAOImpl extends GenericDAOImpl<LogSystemActivity> implements LogSystemActivityDAO {
/* --------------------------- Constructor ---------------------------*/

    public LogSystemActivityDAOImpl() {
        super(LogSystemActivity.class);
    }
}
