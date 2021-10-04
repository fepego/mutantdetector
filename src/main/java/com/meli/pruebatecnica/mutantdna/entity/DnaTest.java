package com.meli.pruebatecnica.mutantdna.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class DnaTest implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column
    @NotNull
    private String dnaKey;

    @Column
    @NotNull
    private boolean ismutant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDnaKey() {
        return dnaKey;
    }

    public void setDnaKey(String dnaKey) {
        this.dnaKey = dnaKey;
    }

    public boolean isIsmutant() {
        return ismutant;
    }

    public void setIsmutant(boolean ismutant) {
        this.ismutant = ismutant;
    }

}
