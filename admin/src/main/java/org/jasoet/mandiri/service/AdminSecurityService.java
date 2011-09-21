package org.jasoet.mandiri.service;


import org.jasoet.mandiri.dao.StaffDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AdminSecurityService implements UserDetailsService {
/*------------------------------ Fields ------------------------------*/

    private StaffDAO staffDAO;

/* --------------------- Getter and Setter ---------------------*/

    public StaffDAO getStaffDAO() {
        return staffDAO;
    }

    @Autowired
    public void setStaffDAO(StaffDAO staffDAO) {
        this.staffDAO = staffDAO;
    }

/* --------------------- Interface UserDetailsService --------------------- */


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException, DataAccessException {
        try {
            UserDetails o = staffDAO.findByUsername(s);
            if (o != null) {
                return o;
            } else {
                throw new UsernameNotFoundException("User Not Found");
            }
        } catch (Exception x) {
            throw new UsernameNotFoundException(x.getMessage());
        }
    }
}
