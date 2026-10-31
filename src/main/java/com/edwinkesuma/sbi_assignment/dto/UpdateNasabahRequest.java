package com.edwinkesuma.sbi_assignment.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UpdateNasabahRequest(

        @NotBlank(message = "Nama lengkap wajib diisi")
        @Size(max = 100, message = "Nama lengkap maksimal 100 karakter")
        String fullName,

        @NotBlank(message = "Alamat wajib diisi")
        @Size(max = 255, message = "Alamat maksimal 255 karakter")
        String address,

        @NotBlank(message = "Tempat lahir wajib diisi")
        @Size(max = 100, message = "Tempat lahir maksimal 100 karakter")
        String birthPlace,

        @NotNull(message = "Tanggal lahir wajib diisi")
        @PastOrPresent(message = "Tanggal lahir tidak boleh di masa depan")
        LocalDate birthDate,

        @NotBlank(message = "Nomor handphone wajib diisi")
        @Pattern(
                regexp = "^628[0-9]{8,11}$",
                message = "Nomor handphone harus menggunakan format 628xxx dan terdiri dari 11-14 digit"
        )
        String phone

) {
}