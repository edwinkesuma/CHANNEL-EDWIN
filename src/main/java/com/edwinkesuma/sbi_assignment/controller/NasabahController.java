package com.edwinkesuma.sbi_assignment.controller;

import com.edwinkesuma.sbi_assignment.dto.CreateNasabahRequest;
import com.edwinkesuma.sbi_assignment.dto.NasabahResponse;
import com.edwinkesuma.sbi_assignment.service.NasabahService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api/nasabah")
public class NasabahController {

    private NasabahService nasabahService;

    public NasabahController(NasabahService nasabahService) {
        this.nasabahService = nasabahService;
    }

    @PostMapping
    public ResponseEntity<NasabahResponse> createNasabah(@Valid @RequestBody CreateNasabahRequest nasabah){
        NasabahResponse savedNasabah = nasabahService.createNasabah(nasabah);

        return new ResponseEntity<>(savedNasabah, HttpStatus.CREATED);
    }
}
