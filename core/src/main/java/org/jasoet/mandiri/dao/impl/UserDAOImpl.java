package org.jasoet.mandiri.dao.impl;

import org.jasoet.mandiri.dao.UserDAO;
import org.jasoet.mandiri.domain.User;
import org.jasoet.mandiri.util.enums.UserStatus;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Repository("userDAOImpl")
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {
    /* --------------------------- Constructor ---------------------------*/
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserDAOImpl.class);

    public UserDAOImpl() {
        super(User.class);
    }

/* --------------------- Interface UserDAO --------------------- */

    @Override
    public Long countBySearchAndStatus(String searchKey, UserStatus status) throws DataAccessException {
        return (Long) this.entityManager.createQuery("SELECT count(o) FROM User o WHERE (o.username LIKE :usernameKey OR o.nama LIKE :namaKey OR o.email LIKE :emailKey " +
                "OR o.alamat LIKE :alamatKey OR o.kota LIKE :kotaKey OR o.nomorRekening LIKE :noRekeningKey ) AND o.status = :status  ORDER BY o.username ASC")
                .setParameter("usernameKey", '%' + searchKey + "%")
                .setParameter("namaKey", '%' + searchKey + "%")
                .setParameter("emailKey", '%' + searchKey + "%")
                .setParameter("alamatKey", '%' + searchKey + "%")
                .setParameter("noRekeningKey", '%' + searchKey + "%")
                .setParameter("status", status)
                .setParameter("kotaKey", '%' + searchKey + "%").getSingleResult();
    }

    @Override
    public Long countBySearch(String searchKey) throws DataAccessException {
        return (Long) this.entityManager.createQuery("SELECT count(o) FROM User o WHERE o.username LIKE :usernameKey OR o.nama LIKE :namaKey OR o.email LIKE :emailKey " +
                "OR o.alamat LIKE :alamatKey OR o.kota LIKE :kotaKey OR o.nomorRekening LIKE :noRekeningKey  ORDER BY o.username ASC")
                .setParameter("usernameKey", '%' + searchKey + "%")
                .setParameter("namaKey", '%' + searchKey + "%")
                .setParameter("emailKey", '%' + searchKey + "%")
                .setParameter("alamatKey", '%' + searchKey + "%")
                .setParameter("noRekeningKey", '%' + searchKey + "%")
                .setParameter("kotaKey", '%' + searchKey + "%").getSingleResult();
    }

    @Override
    public Long countByUserActive() throws DataAccessException {
        return (Long) this.entityManager.createQuery("SELECT count(o) FROM User o WHERE o.status = :status")
                .setParameter("status", UserStatus.ACTIVE)
                .getSingleResult();
    }

    @Override
    public User findByUsername(String username) {
        this.entityManager.clear();

        User result = null;
        try {
            result = (User) this.entityManager.createQuery("SELECT o FROM User o WHERE o.username=:username")
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (DataAccessException ex) {
            result = null;
        } catch (EntityExistsException ex) {
            result = null;
        } catch (Exception ex) {
            result = null;
        }
        return result;
    }

    @Override
    public User findByUsernameActive(String username) throws DataAccessException, EntityNotFoundException {
        this.entityManager.clear();
        return (User) this.entityManager.createQuery("SELECT o FROM User o WHERE o.username=:username and o.status=:status ")
                .setParameter("username", username)
                .setParameter("status", UserStatus.ACTIVE)
                .getSingleResult();
    }

    @Override
    public boolean findAndRemoveOverLimitActivation() {
        try {
            this.entityManager.clear();
            int result = this.entityManager.createNativeQuery("DELETE from user_ WHERE DATEDIFF(CURDATE(), tanggalBergabung) > 2 AND `status` = 0").executeUpdate();

            log.info(result + " rows affected");

            return true;
        } catch (DataAccessException ex) {
            return false;
        } catch (EntityNotFoundException ex) {
            return false;
        }
    }

    public User findByUserActive(int start) throws DataAccessException, EntityNotFoundException {
        this.entityManager.clear();
        return (User) this.entityManager.createQuery("SELECT o FROM User o WHERE o.status=:status ")
                .setParameter("status", UserStatus.ACTIVE)
                .setFirstResult(start)
                .setMaxResults(1)
                .getSingleResult();
    }


    @Override
    public List<User> findBySearchAndStatusLimit(String searchKey, UserStatus status, int start, int size) throws DataAccessException {
        this.entityManager.clear();
        return this.entityManager.createQuery("SELECT o FROM User o WHERE (o.username LIKE :usernameKey OR o.nama LIKE :namaKey OR o.email LIKE :emailKey " +
                "OR o.alamat LIKE :alamatKey OR o.kota LIKE :kotaKey OR o.nomorRekening LIKE :noRekeningKey) AND o.status = :status ORDER BY o.username ASC")
                .setParameter("usernameKey", '%' + searchKey + "%")
                .setParameter("namaKey", '%' + searchKey + "%")
                .setParameter("emailKey", '%' + searchKey + "%")
                .setParameter("alamatKey", '%' + searchKey + "%")
                .setParameter("noRekeningKey", '%' + searchKey + "%")
                .setParameter("kotaKey", '%' + searchKey + "%")
                .setParameter("status", status)
                .setFirstResult(start).setMaxResults(size).getResultList();
    }

    @Override
    public List<User> findByAllLimit(String searchKey, int start, int size) throws DataAccessException {
        this.entityManager.clear();
        return this.entityManager.createQuery("SELECT o FROM User o WHERE o.username LIKE :usernameKey OR o.nama LIKE :namaKey OR o.email LIKE :emailKey " +
                "OR o.alamat LIKE :alamatKey OR o.kota LIKE :kotaKey OR o.nomorRekening LIKE :noRekeningKey  ORDER BY o.username ASC")
                .setParameter("usernameKey", '%' + searchKey + "%")
                .setParameter("namaKey", '%' + searchKey + "%")
                .setParameter("emailKey", '%' + searchKey + "%")
                .setParameter("alamatKey", '%' + searchKey + "%")
                .setParameter("noRekeningKey", '%' + searchKey + "%")
                .setParameter("kotaKey", '%' + searchKey + "%")
                .setFirstResult(start).setMaxResults(size).getResultList();
    }

    @Override
    public List<User> findBySpesificLimit(String usernameKey, String namaKey, String emailKey, String alamatKey, String kotaKey, String noRekeningKey, UserStatus userStatusKey, int start, int size) throws DataAccessException {
        this.entityManager.clear();
        return this.entityManager.createQuery("SELECT o FROM User o WHERE o.username LIKE :usernameKey AND o.nama LIKE :namaKey AND o.email LIKE :emailKey " +
                "AND o.alamat LIKE :alamatKey AND o.kota LIKE :kotaKey AND o.nomorRekening LIKE :noRekeningKey AND o.status = :userStatusKey ORDER BY o.username ASC")
                .setParameter("usernameKey", "%" + usernameKey + "%")
                .setParameter("namaKey", "%" + namaKey + "%")
                .setParameter("emailKey", "%" + emailKey + "%")
                .setParameter("alamatKey", "%" + alamatKey + "%")
                .setParameter("noRekeningKey", "%" + noRekeningKey + "%")
                .setParameter("userStatusKey", userStatusKey)
                .setParameter("kotaKey", "%" + kotaKey + "%")
                .setFirstResult(start).setMaxResults(size).getResultList();
    }


    @Override
    public List<User> getUsersBySponsor(Long sponsor) throws DataAccessException {
        this.entityManager.clear();
        /*return this.entityManager.createQuery("SELECT o FROM User o WHERE o.sponsor.id=:sponsor")
                .setParameter("sponsor", sponsor)
                .getResultList();*/

        return this.entityManager.createNativeQuery("SELECT u.* from user_ u where u.sponsor_id=:sponsor",User.class)
                .setParameter("sponsor", sponsor)
                .getResultList();
    }
}
