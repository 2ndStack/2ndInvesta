package org.jasoet.mandiri.service;


import org.jasoet.mandiri.dao.ProfitBlokirDAO;
import org.jasoet.mandiri.dao.ProfitDAO;
import org.jasoet.mandiri.domain.ProfitBlokir;
import org.jasoet.mandiri.util.enums.Month;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service("monthlyProfitService")
public class MonthlyProfitService {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MonthlyProfitService.class);

    private ProfitDAO profitDAO;
    private ProfitBlokirDAO profitBlokirDAO;

    public ProfitDAO getProfitDAO() {
        return profitDAO;
    }

    @Autowired
    public void setProfitDAO(ProfitDAO profitDAO) {
        this.profitDAO = profitDAO;
    }

    public ProfitBlokirDAO getProfitBlokirDAO() {
        return profitBlokirDAO;
    }

    @Autowired
    public void setProfitBlokirDAO(ProfitBlokirDAO profitBlokirDAO) {
        this.profitBlokirDAO = profitBlokirDAO;
    }

    public void process() {
        Calendar cal = Calendar.getInstance();
        Month month = Month.valueOf(cal.get(Calendar.MONTH));
        int year = cal.get(Calendar.YEAR);

        profitDAO.monthlyProfit(month, year);
        profitBlokirDAO.monthlyProfit(month, year);
        log.info("===================================== monthlyProfit " + month + " " + year + " =====================================");
    }
}
