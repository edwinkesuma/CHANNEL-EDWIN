package com.edwinkesuma.sbi_assignment.service;

import com.edwinkesuma.sbi_assignment.dto.CreateNasabahRequest;
import com.edwinkesuma.sbi_assignment.dto.NasabahResponse;

public interface NasabahService {
    NasabahResponse createNasabah(CreateNasabahRequest nasabahRequest);
}
