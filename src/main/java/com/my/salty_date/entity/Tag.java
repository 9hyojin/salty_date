package com.my.salty_date.entity;

import jakarta.persistence.*;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagIdx;

    @Column
    private String tagName;

}
