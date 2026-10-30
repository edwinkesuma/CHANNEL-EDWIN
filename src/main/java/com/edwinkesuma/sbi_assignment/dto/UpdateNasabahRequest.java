package com.edwinkesuma.sbi_assignment.dto;

import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record UpdateNasabahRequest(
        String fullName,
        String address,
        String birthPlace,
        @PastOrPresent(message = "Tanggal lahir tidak boleh di masa depan")
        LocalDate birthDate,
        String phone
) {
}
