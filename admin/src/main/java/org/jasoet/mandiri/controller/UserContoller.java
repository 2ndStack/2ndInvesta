package org.jasoet.mandiri.controller;

import org.jasoet.mandiri.dao.BankDAO;
import org.jasoet.mandiri.dao.BonusDAO;
import org.jasoet.mandiri.dao.UserDAO;
import org.jasoet.mandiri.domain.Bank;
import org.jasoet.mandiri.domain.Bonus;
import org.jasoet.mandiri.domain.User;
import org.jasoet.mandiri.util.UserObjectJSON;
import org.jasoet.mandiri.util.enums.Month;
import org.jasoet.mandiri.util.enums.ProfitStatus;
import org.jasoet.mandiri.util.enums.UserStatus;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.codec.Hex;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang.RandomStringUtils.randomAlphanumeric;

@Controller
public class UserContoller {
/*------------------------------ Fields ------------------------------*/

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserContoller.class);
    private UserDAO userDAO;
    private BankDAO bankDAO;
    private BonusDAO bonusDAO;
    private PasswordEncoder passwordEncoder;

/* --------------------- Getter and Setter ---------------------*/

    public BonusDAO getBonusDAO() {
        return bonusDAO;
    }

    @Autowired
    public void setBonusDAO(BonusDAO bonusDAO) {
        this.bonusDAO = bonusDAO;
    }

    public BankDAO getBankDAO() {
        return bankDAO;
    }

    @Autowired
    public void setBankDAO(BankDAO bankDAO) {
        this.bankDAO = bankDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /* -------------------------- Other Methods -------------------------- */
    @RequestMapping(value = "/user/index.json", method = RequestMethod.GET)
    public
    @ResponseBody
    UserObjectJSON showJSON(HttpServletRequest request, @RequestParam(value = "sSearch", defaultValue = "") String sSearch,
                            @RequestParam(value = "iDisplayStart", defaultValue = "0") String iDisplayStartS,
                            @RequestParam(value = "iDisplayLength", defaultValue = "0") String iDisplayLengthS,
                            @RequestParam(value = "sEcho", defaultValue = "0") String sEchoS,
                            @RequestParam(value = "sSearch_4", defaultValue = "") String sSearch_4) {

        int iDisplayStart = Integer.parseInt(iDisplayStartS);
        int iDisplayLength = Integer.parseInt(iDisplayLengthS);
        int sEcho = Integer.parseInt(sEchoS);

        UserObjectJSON result;

        if (sSearch_4.equalsIgnoreCase("ALL")) {
            List<User> data = userDAO.findByAllLimit(sSearch, iDisplayStart, iDisplayLength);
            result = new UserObjectJSON(data);
            result.setsEcho(sEcho + 1);
            long resultCount = userDAO.countBySearch(sSearch);
            result.setiTotalRecords(resultCount);
            result.setiTotalDisplayRecords(resultCount);
            return result;
        }

        try {
            UserStatus status = UserStatus.valueOf(sSearch_4);
            List<User> data = userDAO.findBySearchAndStatusLimit(sSearch, status, iDisplayStart, iDisplayLength);
            result = new UserObjectJSON(data);
            result.setsEcho(sEcho + 1);
            long resultCount = userDAO.countBySearchAndStatus(sSearch, status);
            result.setiTotalRecords(resultCount);
            result.setiTotalDisplayRecords(resultCount);
        } catch (IllegalArgumentException ex) {
            List<User> data = userDAO.findBySearchAndStatusLimit(sSearch, UserStatus.INACTIVE, iDisplayStart, iDisplayLength);
            result = new UserObjectJSON(data);
            result.setsEcho(sEcho + 1);
            long resultCount = userDAO.countBySearchAndStatus(sSearch, UserStatus.INACTIVE);
            result.setiTotalRecords(resultCount);
            result.setiTotalDisplayRecords(resultCount);
        }


        return result;
    }

    @RequestMapping(value = "/user/status/{id}/{statusKey}", method = RequestMethod.GET)
    public String changeStatus(@PathVariable("id") Long id, @PathVariable("statusKey") String statusKey) {
        User data = userDAO.get(id);
        UserStatus status = UserStatus.valueOf(statusKey);
        try {
            if (data.getStatus() == UserStatus.INACTIVE && status == UserStatus.ACTIVE) {
                data.setEnabled(true);
                data.setTanggalAktivasi(new Date());
                data.setTanggalTransfer(new Date());
                data.setStatus(status);
                userDAO.merge(data);
                try {
                    Calendar cal = Calendar.getInstance();
                    Month bulan = Month.valueOf(cal.get(Calendar.MONTH));
                    int tahun = cal.get(Calendar.YEAR);
                    Bonus bonus = new Bonus();
                    bonus.setUser(data.getSponsor());
                    bonus.setDownline(data);
                    bonus.setBulan(bulan);
                    bonus.setTahun(tahun);
                    bonus.setKeterangan("Dalam Proses");

                    int jumlah = data.getNilaiDeposit() / 10;
                    bonus.setJumlah(jumlah);
                    bonus.setStatus(ProfitStatus.PENDING);
                    bonusDAO.save(bonus);
                } catch (Exception e) {
                    log.debug("Error Insert bonus : " + e.getMessage());
                }
            } else {
                data.setStatus(status);
                userDAO.merge(data);
            }

        } catch (DataAccessException ex) {
            log.debug("Error Update Status : " + ex.getMessage());
        }

        return "redirect:/user";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String delete(ModelMap modelMap, @PathVariable("id") Long id) {
        User data = userDAO.get(id);

        try {
            userDAO.delete(data);

            modelMap.addAttribute("message", "Record Successfully Deleted");
        } catch (DataAccessException ex) {
            modelMap.addAttribute("message", "Error : Record Failed To Delete");
            log.debug("Error Deleting Row : " + ex.getMessage());
        }
        return "redirect:/user";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        List<User> data = userDAO.getAll();
        modelMap.addAttribute("data", data);

        modelMap.addAttribute("mainNav", "master");
        modelMap.addAttribute("tab", "user");


        return "user/index";
    }

    @RequestMapping(value = "/user/{id}/reset", method = RequestMethod.GET)
    public String reset(ModelMap modelMap, @PathVariable("id") Long id) {
        User data = userDAO.get(id);
        modelMap.addAttribute("data", data);

        modelMap.addAttribute("mainNav", "master");
        modelMap.addAttribute("tab", "user");

        return "user/reset";
    }

    @RequestMapping(value = "/user/{id}/reset", method = RequestMethod.POST)
    public String resetPost(ModelMap modelMap, @PathVariable("id") Long id) {
        User data = userDAO.get(id);

        String password = randomAlphanumeric(6).toLowerCase();
        String salt = new String(Hex.encode(data.getUsername().getBytes()));
        data.setSalt(salt);
        data.setPassword(passwordEncoder.encodePassword(password, salt));
        userDAO.merge(data);

        modelMap.addAttribute("password", password);
        modelMap.addAttribute("data", data);

        modelMap.addAttribute("mainNav", "master");
        modelMap.addAttribute("tab", "user");


        return "user/reset";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String show(ModelMap modelMap, @PathVariable("id") Long id) {
        User data = userDAO.get(id);
        Date dateAct = data.getTanggalAktivasi();

        Calendar calAct = Calendar.getInstance();
        calAct.setTime(dateAct == null ? new Date() : dateAct);
        int yearAct = calAct.get(Calendar.YEAR);
        int monthAct = calAct.get(Calendar.MONTH);
        int dayAct = calAct.get(Calendar.DAY_OF_MONTH);
        if (dayAct > 20) {
            monthAct = monthAct + 1;
        }

        Date dateNow = new Date();
        Calendar calNow = Calendar.getInstance();
        calNow.setTime(dateNow);
        int yearNow = calNow.get(Calendar.YEAR);
        int monthNow = calNow.get(Calendar.MONTH);

        int a = yearNow - yearAct;
        a = a * 12;
        a = a + monthNow;
        int bulanBerjalan = a - monthAct;
        bulanBerjalan = bulanBerjalan + 1;

        modelMap.addAttribute("bulanBerjalan", dateAct != null ? bulanBerjalan : "");
        modelMap.addAttribute("data", data);

        modelMap.addAttribute("mainNav", "master");
        modelMap.addAttribute("tab", "user");

        return "user/show";
    }

    @RequestMapping(value = "/user/{id}/edit", method = RequestMethod.GET)
    public String edit(ModelMap modelMap, @PathVariable("id") Long id) {
        User user = userDAO.get(id);
        modelMap.addAttribute("user", user);

        modelMap.addAttribute("mainNav", "master");
        modelMap.addAttribute("tab", "user");

        List<Bank> listBank = bankDAO.getAll();
        modelMap.addAttribute("listBank", listBank);

        return "user/edit";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String update(HttpServletRequest request, ModelMap modelMap) {

        Long id = Long.valueOf(request.getParameter("id"));
        User user = userDAO.get(id);
        String nama = request.getParameter("nama");
        String alamat = request.getParameter("alamat");
        String kota = request.getParameter("kota");
        String telp = request.getParameter("telp");
        String email = request.getParameter("email");
        String sbank = request.getParameter("bank");
        String nomorRekening = request.getParameter("nomorRekening");

        user.setNama(nama);
        user.setAlamat(alamat);
        user.setKota(kota);
        user.setTelp(telp);
        user.setEmail(email);
        Bank bank = bankDAO.get(Long.valueOf(sbank));
        user.setBank(bank);
        user.setNomorRekening(nomorRekening);

        userDAO.merge(user);

        return "redirect:/user/" + user.getId();
    }
}
