package com.my.salty_date.entity;

import javax.persistence.*;

@Entity
public class DatingTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dateTagIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dateIdx")
    private Dating dating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tagIdx")
    private Tag tag;


}
