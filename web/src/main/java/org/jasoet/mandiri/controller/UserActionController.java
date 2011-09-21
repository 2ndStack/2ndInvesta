package org.jasoet.mandiri.controller;

import org.jasoet.mandiri.dao.BankDAO;
import org.jasoet.mandiri.dao.BeritaDAO;
import org.jasoet.mandiri.dao.BonusDAO;
import org.jasoet.mandiri.dao.PaketDAO;
import org.jasoet.mandiri.dao.ProfitDAO;
import org.jasoet.mandiri.dao.StaffDAO;
import org.jasoet.mandiri.dao.UserDAO;
import org.jasoet.mandiri.domain.Bank;
import org.jasoet.mandiri.domain.Berita;
import org.jasoet.mandiri.domain.Bonus;
import org.jasoet.mandiri.domain.Paket;
import org.jasoet.mandiri.domain.Profit;
import org.jasoet.mandiri.domain.Staff;
import org.jasoet.mandiri.domain.User;
import org.jasoet.mandiri.service.MandiriMailService;
import org.jasoet.mandiri.util.counter.TransferIdCounter;
import org.jasoet.mandiri.util.enums.ProfitStatus;
import org.jasoet.mandiri.util.enums.UserStatus;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.codec.Hex;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

@Controller
public class UserActionController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserActionController.class);

    private UserDAO userDAO;
    private StaffDAO staffDAO;
    private PaketDAO paketDAO;
    private ProfitDAO profitDAO;
    private BonusDAO bonusDAO;
    private BankDAO bankDAO;
    private PasswordEncoder passwordEncoder;
    private SaltSource saltSource;
    private BeritaDAO beritaDAO;
    private MandiriMailService mailService;
    private TransferIdCounter transferIdCounter;


    /* --------------------- Getter and Setter ---------------------*/

    public TransferIdCounter getTransferIdCounter() {
        return transferIdCounter;
    }

    @Autowired
    public void setTransferIdCounter(TransferIdCounter transferIdCounter) {
        this.transferIdCounter = transferIdCounter;
    }

    public MandiriMailService getMailService() {
        return mailService;
    }

    @Autowired
    public void setMailService(MandiriMailService mailService) {
        this.mailService = mailService;
    }

    public BeritaDAO getBeritaDAO() {
        return beritaDAO;
    }

    @Autowired
    public void setBeritaDAO(BeritaDAO beritaDAO) {
        this.beritaDAO = beritaDAO;
    }

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

    public StaffDAO getStaffDAO() {
        return staffDAO;
    }

    @Autowired
    public void setStaffDAO(StaffDAO staffDAO) {
        this.staffDAO = staffDAO;
    }

    public ProfitDAO getProfitDAO() {
        return profitDAO;
    }

    @Autowired
    public void setProfitDAO(ProfitDAO profitDAO) {
        this.profitDAO = profitDAO;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public SaltSource getSaltSource() {
        return saltSource;
    }

    @Autowired
    public void setSaltSource(SaltSource saltSource) {
        this.saltSource = saltSource;
    }


    public UserDAO getUserDAO() {
        return userDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public PaketDAO getPaketDAO() {
        return paketDAO;
    }

    @Autowired
    public void setPaketDAO(PaketDAO paketDAO) {
        this.paketDAO = paketDAO;
    }

    @RequestMapping(value = "/user/{userName}", method = RequestMethod.GET)
    public String personalPage(ModelMap modelMap, @PathVariable("userName") String userName) {

        User user = null;
        try {
            user = userDAO.findByUsername(userName);
        } catch (Exception e) {
            return "user/pagenotfound";
        }
        boolean status = user.getStatus().equals(UserStatus.ACTIVE);
        modelMap.addAttribute("status", status);
        modelMap.addAttribute("user", user);

        return "user/page";
    }

    @RequestMapping(value = "/user/profile", method = RequestMethod.GET)
    public String profile(ModelMap modelMap) {
        Object updated = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (updated instanceof User) {
            User user = (User) updated;
            modelMap.addAttribute("user", user);
        } else {
            return "redirect:/user/login";
        }

        return "user/profile";
    }

    @RequestMapping(value = "/register/{userName}", method = RequestMethod.GET)
    public String registerBySponsor(ModelMap modelMap, @PathVariable("userName") String userName) {

        try {
            User sponsor = userDAO.findByUsername(userName);
            if (sponsor.getStatus() != UserStatus.ACTIVE) {
                return "user/register";
            }
            modelMap.addAttribute("sponsor", sponsor);

            List<Paket> listPaket = paketDAO.getAll();
            modelMap.addAttribute("listPaket", listPaket);

            return "user/register";
        } catch (Exception e) {
            return "redirect:/dashboard";
        }

    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(ModelMap modelMap) {

        List<Paket> listPaket = paketDAO.getAll();
        modelMap.addAttribute("listPaket", listPaket);

        List<Bank> listBank = bankDAO.getAll();
        modelMap.addAttribute("listBank", listBank);

        return "user/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveRegistration(HttpServletRequest request, ModelMap modelMap) {
        //todo check then Save registration

        String ssponsor = request.getParameter("sponsor");

        String kaptchaReceived = request.getParameter("kaptchaInput") != null ? request.getParameter("kaptchaInput") : "";

        String kaptchaExpected = (String) request.getSession()
                .getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);

        if (kaptchaReceived.isEmpty() || !kaptchaReceived.equalsIgnoreCase(kaptchaExpected)) {
            modelMap.addAttribute("errorMessage", "Kode Yang anda Masukkan Salah");
            if (ssponsor == null) {
                return "redirect:/register";
            } else {
                return "redirect:/register/" + ssponsor;
            }
        }

        String username = request.getParameter("username");

        User userCheck = userDAO.findByUsername(username);

        if (userCheck != null) {
            modelMap.addAttribute("errorMessage", "Username Sudah Digunakan");
            if (ssponsor == null) {
                return "redirect:/register";
            } else {
                return "redirect:/register/" + ssponsor;
            }
        }

        String password = request.getParameter("confirmPassword");
        String nama = request.getParameter("nama");
        String alamat = request.getParameter("alamat");
        String kota = request.getParameter("kota");
        String bankId = request.getParameter("bank");
        String nomorRekening = request.getParameter("nomorRekening");
        String namaRekening = request.getParameter("namaRekening");
        String email = request.getParameter("email");
        String telp = request.getParameter("telp");

        Bank bank = bankDAO.get(Long.valueOf(bankId));

        User user = new User();

        String paketId = request.getParameter("paket_id");
        Paket paket = paketDAO.get(Long.valueOf(paketId));
        user.setPaket(paket);
        user.setNilaiDeposit(paket.getHarga());

        Calendar cal = Calendar.getInstance();
        user.setTanggalBergabung(cal.getTime());

        try {
            User sponsorCheck = userDAO.findByUsername(ssponsor);
            if (sponsorCheck == null) {
                Long num = userDAO.countByUserActive();
                Random r = new Random();
                long sid = r.nextLong() % num;
                int iid = (int) Math.abs(sid);
                User sponsor = userDAO.findByUserActive(iid);
                user.setSponsor(sponsor);
            } else {
                user.setSponsor(sponsorCheck);
            }

        } catch (PersistenceException e) {
            Long num = userDAO.countByUserActive();
            Random r = new Random();
            long sid = r.nextLong() % num;
            int iid = (int) Math.abs(sid);
            User sponsor = userDAO.findByUserActive(iid);
            user.setSponsor(sponsor);
        }

        user.setTelp(telp);
        user.setUsername(username);
        user.setNama(nama);
        user.setAlamat(alamat);
        user.setKota(kota);
        user.setBank(bank);
        user.setNomorRekening(nomorRekening);
        user.setNamaRekening(namaRekening);
        user.setEmail(email);
        user.setStatus(UserStatus.INACTIVE);

        String salt = new String(Hex.encode(user.getUsername().getBytes()));
        user.setSalt(salt);
        user.setPassword(passwordEncoder.encodePassword(password, salt));

        Staff admin = staffDAO.findByUsername("admin");
        user.setStaff(admin);


        int nilaiTransfer = user.getNilaiDeposit() + transferIdCounter.getCounter();
        transferIdCounter.increment();

        user.setNilaiTransfer(nilaiTransfer);


        userDAO.save(user);

        Calendar now = Calendar.getInstance();


        final String pesan = "Hai " + user.getNama() + ", Pendaftaran telah kami terima. Silahkan transfer " + nilaiTransfer +
                " untuk melengkapi pendaftaran. Apabila dalam 2 x 24jam tidak dilakukan transfer dan aktivasi maka account akan dihapus." +
                "";

        final String mailto = user.getEmail();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String[] bcc = null;
                String subject = "Registrasi Akun Mandiri";
                mailService.sendEmail(mailto, bcc, subject, pesan);
            }
        }).start();

        modelMap.put("pesan", pesan);
        return "user/complete";
    }

    @RequestMapping(value = "/loginpage", method = RequestMethod.GET)
    public String login() {

        return "user/login";
    }

    @RequestMapping(value = "/user/edit", method = RequestMethod.GET)
    public String edit(ModelMap modelMap) {
        Object updated = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (updated instanceof User) {
            modelMap.addAttribute("user", updated);
            modelMap.put("mainNav", "profil");
        } else {
            return "redirect:/user/login";
        }

        return "user/edit";
    }

    @RequestMapping(value = "/user/edit", method = RequestMethod.POST)
    public String editPost(HttpServletRequest request) {
        Object updated = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (updated instanceof User) {
            User u = (User) updated;
            u.setNama(request.getParameter("nama"));
            u.setAlamat(request.getParameter("alamat"));
            u.setKota(request.getParameter("kota"));
            u.setTelp(request.getParameter("telp"));
            u.setEmail(request.getParameter("email"));
            userDAO.merge(u);
            return "redirect:/user/profile";
        } else {
            return "redirect:/user/login";
        }
    }

    @RequestMapping(value = "/user/profit", method = RequestMethod.GET)
    public String profit(ModelMap modelMap) {
        Object updated = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (updated instanceof User) {
            User u = (User) updated;
            List<Profit> listProfit = profitDAO.findAllByUsername(u.getUsername());
            modelMap.put("listProfit", listProfit);
            int totalPending = 0;
            int totalCompleted = 0;
            for (Profit p : listProfit) {
                if (p.getStatus() == ProfitStatus.PENDING) {
                    totalPending += p.getJumlah();
                } else if (p.getStatus() == ProfitStatus.COMPLETED) {
                    totalCompleted += p.getJumlah();
                }
            }
            modelMap.put("totalPending", totalPending);
            modelMap.put("totalCompleted", totalCompleted);
            modelMap.put("mainNav", "profit");
            return "user/profit";
        } else {
            return "redirect:/user/login";
        }
    }

    @RequestMapping(value = "/user/bonus", method = RequestMethod.GET)
    public String bonus(ModelMap modelMap) {
        Object updated = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (updated instanceof User) {
            User u = (User) updated;
            List<Bonus> listBonus = bonusDAO.findByUsername(u.getUsername());
            modelMap.put("listBonus", listBonus);
            int totalPending = 0;
            int totalCompleted = 0;
            for (Bonus b : listBonus) {
                if (b.getStatus() == ProfitStatus.PENDING) {
                    totalPending += b.getJumlah();
                } else if (b.getStatus() == ProfitStatus.COMPLETED) {
                    totalCompleted += b.getJumlah();
                }
            }
            modelMap.put("totalPending", totalPending);
            modelMap.put("totalCompleted", totalCompleted);
            modelMap.put("mainNav", "bonus");
            return "user/bonus";
        } else {
            return "redirect:/user/login";
        }
    }

    @RequestMapping(value = "/user/downline", method = RequestMethod.GET)
    public String downline(ModelMap modelMap) {
        Object updated = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (updated instanceof User) {
            User u = (User) updated;
            List<User> listUser;
            try {
                listUser = userDAO.getUsersBySponsor(u.getId());
            } catch (Exception e) {
                listUser = new ArrayList<User>();
            }
            modelMap.put("listUser", listUser);
            modelMap.put("mainNav", "downline");
            return "user/downline";
        } else {
            return "redirect:/user/login";
        }
    }

    @RequestMapping(value = "/user/password", method = RequestMethod.GET)
    public String password() {

        return "user/password";
    }

    @RequestMapping(value = "/user/password", method = RequestMethod.POST)
    public String passwordPost(HttpServletRequest request) {
        Object updated = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (updated instanceof User) {
            User u = (User) updated;
            String currentPassword = request.getParameter("currentPassword");
            String newPassword = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmPassword");

            if (passwordEncoder.isPasswordValid(u.getPassword(), currentPassword, u.getSalt())) {
                if (newPassword.equals(confirmPassword)) {
                    u.setPassword(passwordEncoder.encodePassword(newPassword, u.getSalt()));
                    userDAO.merge(u);
                }
            }

            return "redirect:/dashboard";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(ModelMap modelMap) {
        List<Berita> listBerita = beritaDAO.getEntriesOrderByTanggalDesc(0, 1);
        modelMap.put("listBerita", listBerita);
        modelMap.put("mainNav", "dashboard");
        return "user/dashboard";
    }
}
