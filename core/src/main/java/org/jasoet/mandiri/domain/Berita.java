package org.jasoet.mandiri.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "berita")
@Cacheable(value = false)
public class Berita implements DomainObject {
/*------------------------------ Fields ------------------------------*/

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private Staff staff;
    @NotNull
    @Column(length = 255, nullable = false)
    private String nama;
    @Lob
    @NotNull
    @Column(nullable = false)
    private String isi;
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd MMMM yyyy")
    private Date tanggal;

/* --------------------- Getter and Setter ---------------------*/

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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
}
