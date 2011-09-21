package org.jasoet.mandiri.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "bank", uniqueConstraints = @UniqueConstraint(columnNames = {"nama"}))
public class Bank implements DomainObject {
/*------------------------------ Fields ------------------------------*/

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 1, max = 25)
    @Column(length = 25, nullable = false)
    private String nama;

/* --------------------------- Constructor ---------------------------*/

    public Bank() {
    }

    public Bank(String nama) {
        this.nama = nama;
    }

/* --------------------- Getter and Setter ---------------------*/

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

}
