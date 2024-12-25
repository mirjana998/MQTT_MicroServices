package com.example.demo;


import lombok.Data;

import java.io.Serializable;

@Data
public class PlocaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer oblikovana;

    private Integer ravna;

    private Integer lakirana;

    private Double cijena;

    private Integer na_stanju;

}
