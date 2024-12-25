package com.example.demo;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "ploca")
public class Ploca implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "oblikovana", nullable = false)
    private Integer oblikovana;

    @Column(name = "ravna", nullable = false)
    private Integer ravna;

    @Column(name = "lakirana", nullable = false)
    private Integer lakirana;

    @Column(name = "cijena", nullable = false)
    private Double cijena;

    @Column(name = "na_stanju", nullable = false)
    private Integer na_stanju;
}
