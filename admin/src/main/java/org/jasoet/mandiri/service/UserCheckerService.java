package org.jasoet.mandiri.service;

import org.jasoet.mandiri.dao.UserDAO;
import org.jasoet.mandiri.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Deny Prasetyo, S.T.
 * Email : jasoet87@gmail.com
 * Date: 4/16/11
 * Time: 11:48 AM
 */
@Service("userCheckerService")
public class UserCheckerService {
    private UserDAO userDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void check() {
        userDAO.findAndRemoveOverLimitActivation();
    }
}
