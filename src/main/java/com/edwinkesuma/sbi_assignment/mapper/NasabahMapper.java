package com.edwinkesuma.sbi_assignment.mapper;

import com.edwinkesuma.sbi_assignment.dto.CreateNasabahRequest;
import com.edwinkesuma.sbi_assignment.dto.NasabahResponse;
import com.edwinkesuma.sbi_assignment.dto.UpdateNasabahRequest;
import com.edwinkesuma.sbi_assignment.entity.Nasabah;
import org.springframework.stereotype.Component;

@Component
public class NasabahMapper {

    public Nasabah toEntity(CreateNasabahRequest request) {
        Nasabah nasabah = new Nasabah();

        nasabah.setFullName(request.fullName());
        nasabah.setAddress(request.address());
        nasabah.setBirthPlace(request.birthPlace());
        nasabah.setBirthDate(request.birthDate());
        nasabah.setIdentityNumber(request.identityNumber());
        nasabah.setPhone(request.phone());

        return nasabah;
    }

    public NasabahResponse toResponse(Nasabah nasabah) {
        return new NasabahResponse(
                nasabah.getId(),
                nasabah.getFullName(),
                nasabah.getAddress(),
                nasabah.getBirthPlace(),
                nasabah.getBirthDate(),
                nasabah.getIdentityNumber(),
                nasabah.getPhone()
        );
    }
}
