package com.edwinkesuma.sbi_assignment.dto;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record CreateNasabahRequest(
        String fullName,
        String address,
        String birthPlace,
        @PastOrPresent(message = "Tanggal lahir tidak boleh di masa depan")
        LocalDate birthDate,
        String identityNumber,
        @Pattern(
                regexp = "^628[0-9]{8,11}$",
                message = "Nomor handphone harus menggunakan format 628xxx"
        )
        String phone
) {
}
