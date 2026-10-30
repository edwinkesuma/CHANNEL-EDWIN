package com.edwinkesuma.sbi_assignment.controller;

import com.edwinkesuma.sbi_assignment.dto.CreateNasabahRequest;
import com.edwinkesuma.sbi_assignment.dto.NasabahResponse;
import com.edwinkesuma.sbi_assignment.dto.UpdateNasabahRequest;
import com.edwinkesuma.sbi_assignment.service.NasabahService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/api/nasabah")
public class NasabahController {

    private NasabahService nasabahService;

    public NasabahController(NasabahService nasabahService) {
        this.nasabahService = nasabahService;
    }

    @PostMapping
    public ResponseEntity<NasabahResponse> createNasabah(@Valid @RequestBody CreateNasabahRequest nasabah) {
        NasabahResponse savedNasabah = nasabahService.createNasabah(nasabah);

        return new ResponseEntity<>(savedNasabah, HttpStatus.CREATED);
    }

    @GetMapping("/{identityNumber}")
    public ResponseEntity<NasabahResponse> getNasabahByIdentityNumber(@PathVariable String identityNumber) {
        NasabahResponse savedNasabah = nasabahService.getNasabahByIdentityNumber(identityNumber);

        return new ResponseEntity<>(savedNasabah, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<NasabahResponse>> getAllNasabah() {
        List<NasabahResponse> listNasabah = nasabahService.getAllNasabah();

        return new ResponseEntity<>(listNasabah, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NasabahResponse> updateNasabah(@PathVariable Long id, @Valid @RequestBody UpdateNasabahRequest nasabah) {
        NasabahResponse updatedNasabah = nasabahService.updateNasabah(id, nasabah);

        return new ResponseEntity<>(updatedNasabah, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNasabah(@PathVariable Long id) {
        nasabahService.deleteNasabah(id);

        return new ResponseEntity<>("Nasabah berhasil dihapus", HttpStatus.OK);
    }
}
