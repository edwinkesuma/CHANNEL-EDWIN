package com.edwinkesuma.sbi_assignment.service.impl;

import com.edwinkesuma.sbi_assignment.dto.CreateNasabahRequest;
import com.edwinkesuma.sbi_assignment.dto.NasabahResponse;
import com.edwinkesuma.sbi_assignment.entity.Nasabah;
import com.edwinkesuma.sbi_assignment.exception.ResourceNotFoundException;
import com.edwinkesuma.sbi_assignment.mapper.NasabahMapper;
import com.edwinkesuma.sbi_assignment.repository.NasabahRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class NasabahServiceImplTest {

    @Mock
    private NasabahRepository nasabahRepository;

    @Mock
    private NasabahMapper nasabahMapper;

    @InjectMocks
    private NasabahServiceImpl nasabahService;

    @Test
    void createNasabah_shouldReturnNasabahResponse() {
        // Arrange
        CreateNasabahRequest request = new CreateNasabahRequest(
                "Edwin Kesuma",
                "Jl. Mawar",
                "Madiun",
                LocalDate.of(2000, 5, 15),
                "3500123456789001",
                "628123456789"
        );

        Nasabah nasabah = new Nasabah();
        Nasabah savedNasabah = new Nasabah();

        NasabahResponse expectedResponse = new NasabahResponse(
                1L,
                "Edwin Kesuma",
                "Jl. Mawar",
                "Madiun",
                LocalDate.of(2000, 5, 15),
                "3500123456789001",
                "628123456789"
        );

        when(nasabahMapper.toEntity(request))
                .thenReturn(nasabah);

        when(nasabahRepository.save(nasabah))
                .thenReturn(savedNasabah);

        when(nasabahMapper.toResponse(savedNasabah))
                .thenReturn(expectedResponse);

        // Act
        NasabahResponse actualResponse =
                nasabahService.createNasabah(request);

        // Assert
        assertEquals(expectedResponse, actualResponse);

        verify(nasabahMapper).toEntity(request);
        verify(nasabahRepository).save(nasabah);
        verify(nasabahMapper).toResponse(savedNasabah);
    }

    @Test
    void getNasabahByIdentityNumber_shouldReturnNasabahResponse() {
        // Arrange
        String identityNumber = "3500123456789001";

        Nasabah savedNasabah = new Nasabah();

        NasabahResponse expectedResponse = new NasabahResponse(
                1L,
                "Edwin Kesuma",
                "Jl. Mawar",
                "Madiun",
                LocalDate.of(2000, 5, 15),
                identityNumber,
                "628123456789"
        );

        when(nasabahRepository.findByIdentityNumber(identityNumber))
                .thenReturn(Optional.of(savedNasabah));

        when(nasabahMapper.toResponse(savedNasabah))
                .thenReturn(expectedResponse);

        // Act
        NasabahResponse actualResponse =
                nasabahService.getNasabahByIdentityNumber(identityNumber);

        // Assert
        assertEquals(expectedResponse, actualResponse);

        verify(nasabahRepository).findByIdentityNumber(identityNumber);
        verify(nasabahMapper).toResponse(savedNasabah);
    }

    @Test
    void getNasabahByIdentityNumber_shouldThrowResourceNotFoundException() {
        // Arrange
        String identityNumber = "3500123456789001";

        when(nasabahRepository.findByIdentityNumber(identityNumber))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> nasabahService.getNasabahByIdentityNumber(identityNumber)
        );

        assertEquals(
                "Nasabah dengan nomor ktp " + identityNumber + " tidak ditemukan",
                exception.getMessage()
        );

        verify(nasabahRepository).findByIdentityNumber(identityNumber);
        verify(nasabahMapper, never()).toResponse(any());
    }
}