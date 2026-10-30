package com.edwinkesuma.sbi_assignment.service;

import com.edwinkesuma.sbi_assignment.dto.CreateNasabahRequest;
import com.edwinkesuma.sbi_assignment.dto.NasabahResponse;

import java.util.List;

public interface NasabahService {
    NasabahResponse createNasabah(CreateNasabahRequest nasabahRequest);

    NasabahResponse getNasabahByIdentityNumber(String identityNumber);

    List<NasabahResponse> getAllNasabah();
}
