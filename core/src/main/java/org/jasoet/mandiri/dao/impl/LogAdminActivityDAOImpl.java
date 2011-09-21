package org.jasoet.mandiri.dao.impl;

import org.jasoet.mandiri.dao.LogAdminActivityDAO;
import org.jasoet.mandiri.domain.LogAdminActivity;
import org.springframework.stereotype.Repository;

@Repository("logAdminActivityDAOImpl")
public class LogAdminActivityDAOImpl extends GenericDAOImpl<LogAdminActivity> implements LogAdminActivityDAO {
/* --------------------------- Constructor ---------------------------*/

    public LogAdminActivityDAOImpl() {
        super(LogAdminActivity.class);
    }
}
