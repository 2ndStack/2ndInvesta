package org.jasoet.mandiri.domain;

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "paket", uniqueConstraints = {@UniqueConstraint(columnNames = {"nama", "enabled"})})
public class Paket implements DomainObject {
/*------------------------------ Fields ------------------------------*/

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 1, max = 25)
    @Column(length = 25, nullable = false)
    private String nama;

    @NotNull
    @Column(nullable = false)
    private int harga;

    private boolean enabled;

/* --------------------------- Constructor ---------------------------*/

    public Paket() {
    }

    public Paket(String nama, int harga, boolean enabled) {
        this.nama = nama;
        this.harga = harga;
        this.enabled = enabled;
    }

/* --------------------- Getter and Setter ---------------------*/

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
