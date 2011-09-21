package org.jasoet.mandiri.dao;

import org.jasoet.mandiri.domain.User;
import org.jasoet.mandiri.util.enums.UserStatus;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface UserDAO extends GenericDAO<User> {
    /* -------------------------- Other Methods -------------------------- */


    public Long countBySearchAndStatus(String searchKey, UserStatus status) throws DataAccessException;


    public Long countBySearch(String searchKey) throws DataAccessException;

    public Long countByUserActive() throws DataAccessException;

    public User findByUsername(String username);

    public User findByUserActive(int start) throws DataAccessException, EntityNotFoundException;

    public User findByUsernameActive(String username) throws DataAccessException, EntityNotFoundException;


    @Transactional
    public boolean findAndRemoveOverLimitActivation() throws DataAccessException;
    public List<User> findBySearchAndStatusLimit(String searchKey, UserStatus status, int start, int size) throws DataAccessException;

    public List<User> findByAllLimit(String searchKey, int start, int size) throws DataAccessException;

    public List<User> findBySpesificLimit(String usernameKey, String namaKey, String emailKey, String alamatKey,
                                          String kotaKey, String noRekeningKey, UserStatus userStatusKey, int start, int size) throws DataAccessException;

    public List<User> getUsersBySponsor(Long sponsor) throws DataAccessException;
}
