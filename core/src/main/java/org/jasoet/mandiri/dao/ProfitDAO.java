package org.jasoet.mandiri.dao;

import org.jasoet.mandiri.domain.Bank;
import org.jasoet.mandiri.domain.Profit;
import org.jasoet.mandiri.util.enums.Month;
import org.jasoet.mandiri.util.enums.ProfitStatus;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Author : Deny Prasetyo S.T.
 * E-mail : jasoet87@gmail.com
 */

public interface ProfitDAO extends GenericDAO<Profit> {

    public Long countBy(String searchKey, Bank bank, ProfitStatus status, Month month, int year) throws DataAccessException;


    public List<Profit> findByLimit(String searchKey, Bank bank, ProfitStatus status, Month month, int year, int start, int size) throws DataAccessException;

    @Transactional
    public void editMasal(Bank bank, Month month, int year, ProfitStatus status) throws DataAccessException;

    @Transactional(readOnly = true)
    public List<Profit> findByUsername(String username) throws DataAccessException;

    @Transactional(readOnly = true)
    public List<Profit> findAllByUsername(String username) throws DataAccessException;

    @Transactional(readOnly = true)
    public List<Profit> findByBank(Bank bank) throws DataAccessException;

    @Transactional
    public void monthlyProfit(Month month, int year) throws DataAccessException;


    public Long countByMonth(Bank bank, Month month, int year) throws DataAccessException;


    public List<Profit> findByMonth(Bank bank, Month month, int year, int start, int size) throws DataAccessException;

    public Long countByMonthRekening(Bank bank, Month month, int year) throws DataAccessException;

    public List<Map<String, Object>> findByMonthRekening(Bank bank, Month month, int year, int start, int size) throws DataAccessException;
}
