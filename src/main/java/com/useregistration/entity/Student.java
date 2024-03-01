package com.useregistration.entity;

import lombok.Data;
import javax.persistence.*;
@Data
@Entity
@Table(name = "Student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name = "address")
    private String address;
}

