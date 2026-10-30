package com.edwinkesuma.sbi_assignment.service.impl;

import com.edwinkesuma.sbi_assignment.dto.CreateNasabahRequest;
import com.edwinkesuma.sbi_assignment.dto.NasabahResponse;
import com.edwinkesuma.sbi_assignment.dto.UpdateNasabahRequest;
import com.edwinkesuma.sbi_assignment.entity.Nasabah;
import com.edwinkesuma.sbi_assignment.exception.DuplicateResourceException;
import com.edwinkesuma.sbi_assignment.exception.ResourceNotFoundException;
import com.edwinkesuma.sbi_assignment.mapper.NasabahMapper;
import com.edwinkesuma.sbi_assignment.repository.NasabahRepository;
import com.edwinkesuma.sbi_assignment.service.NasabahService;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (nasabahRepository.existsByIdentityNumber(nasabahRequest.identityNumber())) {
            throw new DuplicateResourceException("Nasabah dengan nomor ktp " + nasabahRequest.identityNumber() + " sudah terdaftar");
        }

        Nasabah savedNasabah = nasabahRepository.save(nasabahMapper.toEntity(nasabahRequest));

        return nasabahMapper.toResponse(savedNasabah);
    }

    @Override
    public NasabahResponse getNasabahByIdentityNumber(String identityNumber) {
        Nasabah savedNasabah = nasabahRepository.findByIdentityNumberAndIsDeletedFalse(identityNumber).orElseThrow(() -> new ResourceNotFoundException("Nasabah dengan nomor ktp " + identityNumber + " tidak ditemukan"));
        return nasabahMapper.toResponse(savedNasabah);
    }

    @Override
    public List<NasabahResponse> getAllNasabah() {
        List<Nasabah> listNasabah = nasabahRepository.findAllByIsDeletedFalse();

        return listNasabah.stream().map(nasabah -> nasabahMapper.toResponse(nasabah)).toList();
    }

    @Override
    public NasabahResponse updateNasabah(Long id, UpdateNasabahRequest nasabah) {
        Nasabah savedNasabah = nasabahRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nasabah dengan nomor ktp " + id + " tidak ditemukan"));

        nasabahMapper.updateEntity(savedNasabah, nasabah);

        Nasabah updatedNasabah = nasabahRepository.save(savedNasabah);

        return nasabahMapper.toResponse(updatedNasabah);
    }

    @Override
    public void deleteNasabah(Long id) {
        Nasabah savedNasabah = nasabahRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nasabah dengan id " + id + " tidak ditemukan"
                ));

        savedNasabah.setDeleted(true);
        nasabahRepository.save(savedNasabah);
    }
}
