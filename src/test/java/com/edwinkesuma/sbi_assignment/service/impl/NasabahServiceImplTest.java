package com.edwinkesuma.sbi_assignment.service.impl;

import com.edwinkesuma.sbi_assignment.dto.CreateNasabahRequest;
import com.edwinkesuma.sbi_assignment.dto.NasabahResponse;
import com.edwinkesuma.sbi_assignment.dto.UpdateNasabahRequest;
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
import java.util.List;
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

    @Test
    void getAllNasabah_shouldReturnListOfNasabahResponse() {
        // Arrange
        Nasabah nasabah1 = new Nasabah();
        Nasabah nasabah2 = new Nasabah();

        List<Nasabah> listNasabah = List.of(nasabah1, nasabah2);

        NasabahResponse response1 = new NasabahResponse(
                1L,
                "Edwin Kesuma",
                "Jl. Mawar",
                "Madiun",
                LocalDate.of(2000, 5, 15),
                "3500123456789001",
                "628123456789"
        );

        NasabahResponse response2 = new NasabahResponse(
                2L,
                "Budi Santoso",
                "Jl. Melati",
                "Surabaya",
                LocalDate.of(1999, 8, 20),
                "3500123456789002",
                "628987654321"
        );

        List<NasabahResponse> expectedResponse =
                List.of(response1, response2);

        when(nasabahRepository.findAll())
                .thenReturn(listNasabah);

        when(nasabahMapper.toResponse(nasabah1))
                .thenReturn(response1);

        when(nasabahMapper.toResponse(nasabah2))
                .thenReturn(response2);

        // Act
        List<NasabahResponse> actualResponse =
                nasabahService.getAllNasabah();

        // Assert
        assertEquals(expectedResponse, actualResponse);

        verify(nasabahRepository).findAll();
        verify(nasabahMapper).toResponse(nasabah1);
        verify(nasabahMapper).toResponse(nasabah2);
    }

    @Test
    void getAllNasabah_shouldReturnEmptyListWhenNoNasabahExists() {
        // Arrange
        when(nasabahRepository.findAll())
                .thenReturn(List.of());

        // Act
        List<NasabahResponse> actualResponse =
                nasabahService.getAllNasabah();

        // Assert
        assertNotNull(actualResponse);
        assertTrue(actualResponse.isEmpty());

        verify(nasabahRepository).findAll();
        verify(nasabahMapper, never()).toResponse(any());
    }

    @Test
    void updateNasabah_shouldReturnUpdatedNasabahResponse() {
        // Arrange
        Long id = 1L;

        UpdateNasabahRequest request = new UpdateNasabahRequest(
                "Edwin Kesuma Updated",
                "Jl. Melati",
                "Surabaya",
                LocalDate.of(2000, 5, 15),
                "628987654321"
        );

        Nasabah savedNasabah = new Nasabah();

        NasabahResponse expectedResponse = new NasabahResponse(
                1L,
                "Edwin Kesuma Updated",
                "Jl. Melati",
                "Surabaya",
                LocalDate.of(2000, 5, 15),
                "3500123456789001",
                "628987654321"
        );

        when(nasabahRepository.findById(id))
                .thenReturn(Optional.of(savedNasabah));

        when(nasabahRepository.save(savedNasabah))
                .thenReturn(savedNasabah);

        when(nasabahMapper.toResponse(savedNasabah))
                .thenReturn(expectedResponse);

        // Act
        NasabahResponse actualResponse =
                nasabahService.updateNasabah(id, request);

        // Assert
        assertEquals(expectedResponse, actualResponse);

        verify(nasabahRepository).findById(id);
        verify(nasabahMapper).updateEntity(savedNasabah, request);
        verify(nasabahRepository).save(savedNasabah);
        verify(nasabahMapper).toResponse(savedNasabah);
    }

    @Test
    void updateNasabah_shouldThrowResourceNotFoundExceptionWhenNasabahNotFound() {
        // Arrange
        Long id = 1L;

        UpdateNasabahRequest request = new UpdateNasabahRequest(
                "Edwin Kesuma Updated",
                "Jl. Melati",
                "Surabaya",
                LocalDate.of(2000, 5, 15),
                "628987654321"
        );

        when(nasabahRepository.findById(id))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> nasabahService.updateNasabah(id, request)
        );

        assertEquals(
                "Nasabah dengan nomor ktp " + id + " tidak ditemukan",
                exception.getMessage()
        );

        verify(nasabahRepository).findById(id);

        verify(nasabahMapper, never())
                .updateEntity(any(), any());

        verify(nasabahRepository, never()).save(any());

        verify(nasabahMapper, never()).toResponse(any());
    }

}