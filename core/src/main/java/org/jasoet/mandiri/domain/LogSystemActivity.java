package org.jasoet.mandiri.domain;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "log_system_activity")
public class LogSystemActivity implements DomainObject {
/*------------------------------ Fields ------------------------------*/

    @Id
    @GeneratedValue
    private Long id;

    private String aktor;

    @Lob
    private String keterangan;

    @Temporal(TemporalType.TIMESTAMP)
    private Date waktu;

/* --------------------------- Constructor ---------------------------*/

    public LogSystemActivity() {
    }

    public LogSystemActivity(String aktor, String keterangan, Date waktu) {
        this.aktor = aktor;
        this.keterangan = keterangan;
        this.waktu = waktu;
    }

/* --------------------- Getter and Setter ---------------------*/

    public String getAktor() {
        return aktor;
    }

    public void setAktor(String aktor) {
        this.aktor = aktor;
    }

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

    public Date getWaktu() {
        return waktu;
    }

    public void setWaktu(Date waktu) {
        this.waktu = waktu;
    }
}
