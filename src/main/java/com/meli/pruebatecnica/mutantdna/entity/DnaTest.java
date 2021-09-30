package com.meli.pruebatecnica.mutantdna.entity;

import javax.persistence.*;

@Entity
@Table(name="dna_analysis")
public class DnaTest {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="dna_chain")
    private String dnaChain;


}
