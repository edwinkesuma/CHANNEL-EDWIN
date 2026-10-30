package com.edwinkesuma.sbi_assignment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "nasabah")
@Getter
@Setter
@NoArgsConstructor
public class Nasabah {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String address;

    private String birthPlace;

    private LocalDate birthDate;

    private String identityNumber;

    private String phone;

    @Column(nullable = false)
    private boolean isDeleted = false;
}
