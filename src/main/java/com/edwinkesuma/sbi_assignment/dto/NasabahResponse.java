package com.edwinkesuma.sbi_assignment.dto;

import java.time.LocalDate;

public record NasabahResponse(
        Long id,
        String fullName,
        String address,
        String birthPlace,
        LocalDate birthDate,
        String identityNumber,
        String phone
) {
}
