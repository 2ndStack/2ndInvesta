package org.jasoet.mandiri.util.provider;

import org.jasoet.mandiri.dao.*;
import org.jasoet.mandiri.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.codec.Hex;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class InitialDataProvider {
/*------------------------------ Fields ------------------------------*/

    private RoleDAO roleDAO;
    private BankDAO bankDAO;
    private StaffDAO staffDAO;
    private PaketDAO paketDAO;
    private LogSystemActivityDAO logSystemActivityDAO;
    private PasswordEncoder passwordEncoder;
    private ConfigPropertyDAO configPropertyDAO;

/* --------------------- Getter and Setter ---------------------*/

    public ConfigPropertyDAO getConfigPropertyDAO() {
        return configPropertyDAO;
    }

    @Autowired
    public void setConfigPropertyDAO(ConfigPropertyDAO configPropertyDAO) {
        this.configPropertyDAO = configPropertyDAO;
    }

    public LogSystemActivityDAO getLogSystemActivityDAO() {
        return logSystemActivityDAO;
    }

    public BankDAO getBankDAO() {
        return bankDAO;
    }

    @Autowired
    public void setBankDAO(BankDAO bankDAO) {
        this.bankDAO = bankDAO;
    }

    public PaketDAO getPaketDAO() {
        return paketDAO;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public RoleDAO getRoleDAO() {
        return roleDAO;
    }

    public StaffDAO getStaffDAO() {
        return staffDAO;
    }

/* -------------------------- Other Methods -------------------------- */

    @Autowired
    public void setLogSystemActivityDAO(LogSystemActivityDAO logSystemActivityDAO) {
        this.logSystemActivityDAO = logSystemActivityDAO;
        provideInitData();
    }

    private void provideInitData() {
        if (roleDAO != null && staffDAO != null && paketDAO != null && logSystemActivityDAO != null && passwordEncoder != null) {
            if (roleDAO.count() == 0) {
                if (roleDAO.get("ROLE_ADMIN") == null) {
                    roleDAO.save(new Role("ROLE_ADMIN"));
                    logSystemActivityDAO.save(new LogSystemActivity(InitialDataProvider.class.getName(), "Save ROLE_ADMIN for Initial Data", new Date()));
                }

                if (roleDAO.get("ROLE_FINANCE_MANDIRI") == null) {
                    roleDAO.save(new Role("ROLE_FINANCE_MANDIRI"));
                    logSystemActivityDAO.save(new LogSystemActivity(InitialDataProvider.class.getName(), "Save ROLE_FINANCE for Initial Data", new Date()));
                }
                if (roleDAO.get("ROLE_FINANCE_BCA") == null) {
                    roleDAO.save(new Role("ROLE_FINANCE_BCA"));
                    logSystemActivityDAO.save(new LogSystemActivity(InitialDataProvider.class.getName(), "Save ROLE_FINANCE for Initial Data", new Date()));
                }
                if (roleDAO.get("ROLE_FINANCE_BNI") == null) {
                    roleDAO.save(new Role("ROLE_FINANCE_BNI"));
                    logSystemActivityDAO.save(new LogSystemActivity(InitialDataProvider.class.getName(), "Save ROLE_FINANCE for Initial Data", new Date()));
                }
                if (roleDAO.get("ROLE_FINANCE_BRI") == null) {
                    roleDAO.save(new Role("ROLE_FINANCE_BRI"));
                    logSystemActivityDAO.save(new LogSystemActivity(InitialDataProvider.class.getName(), "Save ROLE_FINANCE for Initial Data", new Date()));
                }
                if (roleDAO.get("ROLE_FINANCE_LAIN") == null) {
                    roleDAO.save(new Role("ROLE_FINANCE_LAIN"));
                    logSystemActivityDAO.save(new LogSystemActivity(InitialDataProvider.class.getName(), "Save ROLE_FINANCE for Initial Data", new Date()));
                }

                if (roleDAO.get("ROLE_STAFF") == null) {
                    roleDAO.save(new Role("ROLE_STAFF"));
                    logSystemActivityDAO.save(new LogSystemActivity(InitialDataProvider.class.getName(), "Save ROLE_STAFF for Initial Data", new Date()));
                }

            }
            if (configPropertyDAO.count() == 0) {
                ConfigProperty cf = new ConfigProperty();
                cf.setKeyz("biayaTransfer");
                cf.setText("0");
                configPropertyDAO.save(cf);
            }
//            if (bankDAO.count() == 0) {
//                Bank b = new Bank();
//                b.setId(1l);
//                b.setNama("BCA");
//                bankDAO.save(b);
//
//                b = new Bank();
//                b.setId(2l);
//                b.setNama("BNI");
//                bankDAO.save(b);
//
//                b = new Bank();
//                b.setId(3l);
//                b.setNama("MANDIRI");
//
//                bankDAO.save(b);
//            }

            if (staffDAO.count() == 0) {
                Role roleAdmin = roleDAO.get("ROLE_ADMIN");

                Staff staff = new Staff();
                staff.setEnabled(true);
                staff.setUsername("admin");
                staff.setTelephone("");
                staff.setPassword("admin");

                List<Role> roles = new ArrayList<Role>();
                roles.add(roleAdmin);

                staff.setRoles(roles);

                String salt = new String(Hex.encode(staff.getUsername().getBytes()));
                staff.setSalt(salt);
                staff.setPassword(passwordEncoder.encodePassword(staff.getPassword(), salt));
                staffDAO.save(staff);

                logSystemActivityDAO.save(new LogSystemActivity(InitialDataProvider.class.getName(),
                        "Create Staff with ROLE_ADMIN Role for Initial Data",
                        new Date()));
            }

            if (paketDAO.count() == 0) {
                paketDAO.save(new Paket("Paket A", 300000, true));
                logSystemActivityDAO.save(new LogSystemActivity(InitialDataProvider.class.getName(),
                        "Create Paket, Nama : 'Paket A', Harga : 300000, Enabled: true for Initial Data",
                        new Date()));

                paketDAO.save(new Paket("Paket B", 1000000, true));
                logSystemActivityDAO.save(new LogSystemActivity(InitialDataProvider.class.getName(),
                        "Create Paket, Nama : 'Paket B', Harga : 1000000, Enabled: true for Initial Data",
                        new Date()));

                paketDAO.save(new Paket("Paket C", 5000000, true));
                logSystemActivityDAO.save(new LogSystemActivity(InitialDataProvider.class.getName(),
                        "Create Paket, Nama : 'Paket C', Harga : 5000000, Enabled: true for Initial Data",
                        new Date()));

                paketDAO.save(new Paket("Paket D", 10000000, true));
                logSystemActivityDAO.save(new LogSystemActivity(InitialDataProvider.class.getName(),
                        "Create Paket, Nama : 'Paket D', Harga : 10000000, Enabled: true for Initial Data",
                        new Date()));

                paketDAO.save(new Paket("Paket E", 20000000, true));
                logSystemActivityDAO.save(new LogSystemActivity(InitialDataProvider.class.getName(),
                        "Create Paket, Nama : 'Paket E', Harga : 20000000, Enabled: true for Initial Data",
                        new Date()));

                paketDAO.save(new Paket("Paket F", 30000000, true));
                logSystemActivityDAO.save(new LogSystemActivity(InitialDataProvider.class.getName(),
                        "Create Paket, Nama : 'Paket F', Harga : 30000000, Enabled: true for Initial Data",
                        new Date()));

                paketDAO.save(new Paket("Paket G", 50000000, true));
                logSystemActivityDAO.save(new LogSystemActivity(InitialDataProvider.class.getName(),
                        "Create Paket, Nama : 'Paket G', Harga : 50000000, Enabled: true for Initial Data",
                        new Date()));
            }
        }
    }

    @Autowired
    public void setPaketDAO(PaketDAO paketDAO) {
        this.paketDAO = paketDAO;
        provideInitData();
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        provideInitData();
    }

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
        provideInitData();
    }

    @Autowired
    public void setStaffDAO(StaffDAO staffDAO) {
        this.staffDAO = staffDAO;
        provideInitData();
    }
}
