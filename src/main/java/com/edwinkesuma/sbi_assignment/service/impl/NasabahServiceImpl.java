package com.edwinkesuma.sbi_assignment.service.impl;

import com.edwinkesuma.sbi_assignment.dto.CreateNasabahRequest;
import com.edwinkesuma.sbi_assignment.dto.NasabahResponse;
import com.edwinkesuma.sbi_assignment.entity.Nasabah;
import com.edwinkesuma.sbi_assignment.exception.ResourceNotFoundException;
import com.edwinkesuma.sbi_assignment.mapper.NasabahMapper;
import com.edwinkesuma.sbi_assignment.repository.NasabahRepository;
import com.edwinkesuma.sbi_assignment.service.NasabahService;
import org.springframework.stereotype.Service;

@Service
public class NasabahServiceImpl implements NasabahService {

    private NasabahRepository nasabahRepository;
    private NasabahMapper nasabahMapper;

    public NasabahServiceImpl(NasabahRepository nasabahRepository, NasabahMapper nasabahMapper) {
        this.nasabahRepository = nasabahRepository;
        this.nasabahMapper = nasabahMapper;
    }

    @Override
    public NasabahResponse createNasabah(CreateNasabahRequest nasabahRequest) {
        Nasabah savedNasabah = nasabahRepository.save(nasabahMapper.toEntity(nasabahRequest));

        return nasabahMapper.toResponse(savedNasabah);
    }

    @Override
    public NasabahResponse getNasabahByIdentityNumber(String identityNumber) {
        Nasabah savedNasabah = nasabahRepository.findByIdentityNumber(identityNumber).orElseThrow(() -> new ResourceNotFoundException("Nasabah dengan nomor ktp " + identityNumber + " tidak ditemukan"));
        return nasabahMapper.toResponse(savedNasabah);
    }
}
