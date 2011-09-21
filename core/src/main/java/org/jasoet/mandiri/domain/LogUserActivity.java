package org.jasoet.mandiri.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "log_user_activity")
public class LogUserActivity implements DomainObject {
/*------------------------------ Fields ------------------------------*/

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @Lob
    private String keterangan;

    @Temporal(TemporalType.TIMESTAMP)
    private Date waktu;

/* --------------------- Getter and Setter ---------------------*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getWaktu() {
        return waktu;
    }

    public void setWaktu(Date waktu) {
        this.waktu = waktu;
    }
}
