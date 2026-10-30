package com.edwinkesuma.sbi_assignment.service;

import com.edwinkesuma.sbi_assignment.dto.CreateNasabahRequest;
import com.edwinkesuma.sbi_assignment.dto.NasabahResponse;
import com.edwinkesuma.sbi_assignment.dto.UpdateNasabahRequest;

import java.util.List;

public interface NasabahService {
    NasabahResponse createNasabah(CreateNasabahRequest nasabahRequest);

    NasabahResponse getNasabahByIdentityNumber(String identityNumber);

    List<NasabahResponse> getAllNasabah();

    NasabahResponse updateNasabah(Long id, UpdateNasabahRequest nasabah);

    void deleteNasabah(Long id);
}
