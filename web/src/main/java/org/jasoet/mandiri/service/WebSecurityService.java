package org.jasoet.mandiri.service;


import org.jasoet.mandiri.dao.UserDAO;
import org.jasoet.mandiri.domain.User;
import org.jasoet.mandiri.util.enums.UserStatus;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class WebSecurityService implements UserDetailsService {
    private UserDAO userDAO;
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(WebSecurityService.class);

    public UserDAO getUserDAO() {
        return userDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException, DataAccessException {
        try {
            UserDetails o = userDAO.findByUsername(s);

            if (o != null) {
                log.info("================================ bener =======================");
                User u = (User) o;
                log.info("Status User Yang login " + u.getStatus());
                if (u.getStatus() != UserStatus.INACTIVE) {
                     log.info("================================ Harusnya Bisa login =======================");
                    return o;
                } else {
                    throw new UsernameNotFoundException("User Not Allowed");
                }
            } else {
                log.info("================================ salah =======================");
                throw new UsernameNotFoundException("User Not Found");
            }
        } catch (Exception x) {
            log.info("================================ luwih salah =======================");

            throw new UsernameNotFoundException(x.getMessage());
        }
    }
}
