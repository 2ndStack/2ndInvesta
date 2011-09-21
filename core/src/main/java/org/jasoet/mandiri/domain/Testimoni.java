package org.jasoet.mandiri.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Cacheable;
import javax.persistence.Column;
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
@Table(name = "testimoni")
@Cacheable(value = false)
public class Testimoni implements DomainObject {
/*------------------------------ Fields ------------------------------*/

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private User user;
    @Column(length = 35, nullable = false)
    private String nama;
    @Column(length = 45, nullable = false)
    private String kota;
    @Column(length = 25, nullable = false)
    private String email;
    @Column(length = 55, nullable = false)
    private String judul;
    @Lob
    private String isi;
    @DateTimeFormat(pattern = "dd MMMM yyyy")
    @Temporal(TemporalType.DATE)
    private Date tanggal;

/* --------------------- Getter and Setter ---------------------*/

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
