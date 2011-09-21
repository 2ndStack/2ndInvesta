package org.jasoet.mandiri.dao.impl;

import org.jasoet.mandiri.dao.BonusDAO;
import org.jasoet.mandiri.domain.Bank;
import org.jasoet.mandiri.domain.Bonus;
import org.jasoet.mandiri.domain.User;
import org.jasoet.mandiri.util.enums.Month;
import org.jasoet.mandiri.util.enums.ProfitStatus;
import org.jasoet.mandiri.util.enums.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Repository("bonusDAOImpl")
public class BonusDAOImpl extends GenericDAOImpl<Bonus> implements BonusDAO {
/* --------------------------- Constructor ---------------------------*/

    public BonusDAOImpl() {
        super(Bonus.class);
    }

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void monthlyBonus(Month month, int year) throws DataAccessException {

        List<User> listUser = this.entityManager.createQuery("SELECT u FROM User u WHERE u.status = :statusKey ")
                .setParameter("statusKey", UserStatus.ACTIVE)
                .getResultList();

        for (User user : listUser) {
            Calendar fromDatabase = Calendar.getInstance();
            fromDatabase.setTime(user.getTanggalAktivasi());
            int selisihTahun = (year - fromDatabase.get(Calendar.YEAR)) * 12;
            int selisihBulan = (selisihTahun + month.ordinal()) - fromDatabase.get(Calendar.MONTH);

            int tanggalFromDatabase = fromDatabase.get(Calendar.DAY_OF_MONTH);
            if (tanggalFromDatabase >= 21) {
                selisihBulan = selisihBulan - 1;
            }

            if (selisihBulan == 0) {
                int nilaiDeposit = user.getNilaiDeposit();
                float jumlah = 0;

                if (tanggalFromDatabase >= 1 && tanggalFromDatabase <= 5) {
                    jumlah = (nilaiDeposit * 20) / 100;
                } else if (tanggalFromDatabase >= 6 && tanggalFromDatabase <= 10) {
                    jumlah = (nilaiDeposit * 15) / 100;
                } else if
                        (tanggalFromDatabase >= 11 && tanggalFromDatabase <= 20) {
                    jumlah = (nilaiDeposit * 10) / 100;
                } else if
                        (tanggalFromDatabase >= 21) {
                    jumlah = (nilaiDeposit * 20) / 100;
                }

                Bonus bonus = new Bonus();
                bonus.setUser(user);
                bonus.setStatus(ProfitStatus.PENDING);
                bonus.setBulan(month);
                bonus.setTahun(year);
                bonus.setKeterangan("");
                bonus.setJumlah((int) jumlah);

                this.entityManager.persist(bonus);
            } else if (selisihBulan > 17) {
                user.setStatus(UserStatus.EXPIRED);
                this.entityManager.merge(user);

            } else if (selisihBulan > 0 && selisihBulan < 18) {

                int nilaiDeposit = user.getNilaiDeposit();
                float jumlah = (nilaiDeposit * 20) / 100;
                Bonus bonus = new Bonus();
                bonus.setUser(user);
                bonus.setStatus(ProfitStatus.PENDING);
                bonus.setBulan(month);
                bonus.setTahun(year);
                bonus.setKeterangan("");
                bonus.setJumlah((int) jumlah);

                this.entityManager.persist(bonus);

                if (selisihBulan == 17) {
                    user.setStatus(UserStatus.EXPIRED);
                    this.entityManager.merge(user);
                }
            }
        }

    }

    public Long countBy(String searchKey, Bank bank, ProfitStatus status, Month month, int year) throws DataAccessException {
        return (Long) this.entityManager.createQuery("SELECT count(o) FROM Bonus o WHERE o.user.username LIKE :userKey " +
                "AND o.status = :statusKey AND o.bulan = :monthKey AND o.tahun = :yearKey AND o.user.bank =:bank ORDER BY o.bulan DESC")
                .setParameter("userKey", '%' + searchKey + "%")
                .setParameter("statusKey", status)
                .setParameter("monthKey", month)
                .setParameter("yearKey", year)
                .setParameter("bank", bank)
                .getSingleResult();
    }

    public List<Bonus> findByLimit(String searchKey, Bank bank, ProfitStatus status, Month month, int year, int start, int size) throws DataAccessException {
        this.entityManager.clear();
        return this.entityManager.createQuery("SELECT o FROM Bonus o WHERE o.user.username LIKE :userKey " +
                "AND o.status = :statusKey AND o.bulan = :monthKey AND o.tahun = :yearKey AND o.user.bank =:bank ORDER BY o.bulan DESC")
                .setParameter("userKey", '%' + searchKey + "%")
                .setParameter("statusKey", status)
                .setParameter("monthKey", month)
                .setParameter("yearKey", year)
                .setParameter("bank", bank)
                .setFirstResult(start)
                .setMaxResults(size)
                .getResultList();
    }

    public List<Bonus> findByUsername(String username) throws DataAccessException {
        this.entityManager.clear();
        return this.entityManager.createQuery("SELECT o FROM Bonus o WHERE o.user.username = :username  ORDER BY o.id DESC")
                .setParameter("username", username)
                .getResultList();
    }

    public List<Bonus> findAllByUsername(String username) throws DataAccessException {
        this.entityManager.clear();
        return this.entityManager.createQuery("SELECT o FROM Bonus o WHERE o.user.username = :username ORDER BY o.id DESC")
                .setParameter("username", username)
                .getResultList();
    }

    public List<Bonus> findByBank(Bank bank) throws DataAccessException {
        this.entityManager.clear();
        return this.entityManager.createQuery("SELECT o FROM Bonus o WHERE o.user.bank = :bank AND o.status = :status ORDER BY o.id DESC")
                .setParameter("bank", bank)
                .setParameter("status", ProfitStatus.COMPLETED)
                .getResultList();
    }

    public void editMasal(Bank bank, Month month, int year, ProfitStatus status) throws DataAccessException {
        this.entityManager.clear();

        /* entityManager.createQuery("UPDATE Bonus o  SET o.status = :statusKey WHERE o.userOwner.bank=:bank AND o.bulan = :monthKey AND o.tahun = :yearKey ")
        .setParameter("statusKey", status)
        .setParameter("monthKey", month)
        .setParameter("bank", bank)
        .setParameter("yearKey", year)
        .executeUpdate();*/

        entityManager.createNativeQuery("UPDATE bonus o INNER JOIN user_ u ON (o.user_id = u.id) SET o.status =:statusKey WHERE u.bank_id=:bank AND o.bulan =:monthKey AND o.tahun=:yearKey")
                .setParameter("statusKey", status.ordinal())
                .setParameter("monthKey", month.ordinal())
                .setParameter("bank", bank.getId())
                .setParameter("yearKey", year).executeUpdate();
    }

    public Long countByMonth(Bank bank, Month month, int year) throws DataAccessException {
        return (Long) this.entityManager.createQuery("SELECT count(o) FROM Bonus o WHERE o.bulan = :monthKey AND o.tahun = :yearKey AND o.user.bank=:bank ORDER BY o.bulan DESC")
                .setParameter("monthKey", month)
                .setParameter("yearKey", year)
                .setParameter("bank", bank)
                .getSingleResult();
    }

    public List<Bonus> findByMonth(Bank bank, Month month, int year, int start, int size) throws DataAccessException {
        this.entityManager.clear();
        return this.entityManager.createQuery("SELECT o FROM Bonus o WHERE o.bulan = :monthKey AND o.tahun = :yearKey AND o.user.bank=:bank ORDER BY o.bulan DESC")
                .setParameter("monthKey", month)
                .setParameter("yearKey", year)
                .setParameter("bank", bank)
                .setFirstResult(start).setMaxResults(size).getResultList();
    }

    public Long countByMonthRekening(Bank bank, Month month, int year) throws DataAccessException {
        return (Long) this.entityManager.createQuery("SELECT count(o) FROM Bonus o WHERE o.bulan = :monthKey AND o.tahun = :yearKey AND o.user.bank=:bank GROUP BY o.user.nomorRekening ORDER BY o.bulan DESC")
                .setParameter("monthKey", month)
                .setParameter("yearKey", year)
                .setParameter("bank", bank)
                .getSingleResult();
    }

    public List<Map<String, Object>> findByMonthRekening(Bank bank, Month month, int year, int start, int size) throws DataAccessException {
        this.entityManager.clear();
        return jdbcTemplate.queryForList("SELECT u.nomorRekening,u.namaRekening,SUM(p.jumlah) as jumlah,b.nama as namaBank FROM bonus p INNER JOIN user_ u ON (p.user_id=u.id) INNER JOIN bank b ON (b.id = u.bank_id) WHERE p.bulan=? AND p.tahun=? AND u.bank_id=? GROUP BY u.nomorRekening", month.ordinal(), year, bank.getId());
    }
}
