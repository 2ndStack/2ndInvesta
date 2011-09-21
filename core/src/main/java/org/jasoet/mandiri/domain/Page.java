package org.jasoet.mandiri.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "page")
@Cacheable(value = false)
public class Page implements DomainObject {
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

    private String mainNav;

/* --------------------- Getter and Setter ---------------------*/

    public String getMainNav() {
        return mainNav;
    }

    public void setMainNav(String mainNav) {
        this.mainNav = mainNav;
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
