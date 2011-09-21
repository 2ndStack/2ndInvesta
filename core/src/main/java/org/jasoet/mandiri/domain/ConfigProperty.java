package org.jasoet.mandiri.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "config_property", uniqueConstraints = @UniqueConstraint(columnNames = {"keyz"}))
public class ConfigProperty implements DomainObject {
/*------------------------------ Fields ------------------------------*/

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 1, max = 25)
    @Column(length = 25, nullable = false)
    private String keyz;

    @NotNull
    private String text;

/* --------------------------- Constructor ---------------------------*/

    public ConfigProperty() {
    }

    public ConfigProperty(String keyz, String text) {
        this.keyz = keyz;
        this.text = text;
    }

/* --------------------- Getter and Setter ---------------------*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyz() {
        return keyz;
    }

    public void setKeyz(String key) {
        this.keyz = key;
    }

    public String getText() {
        return text;
    }

    public void setText(String value) {
        this.text = value;
    }
}
