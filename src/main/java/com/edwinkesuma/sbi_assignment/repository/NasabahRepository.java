package com.edwinkesuma.sbi_assignment.repository;

import com.edwinkesuma.sbi_assignment.entity.Nasabah;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NasabahRepository extends JpaRepository<Nasabah, Long> {
    Optional<Nasabah> findByIdentityNumber(String identityNumber);

    Optional<Nasabah> findByIdAndIsDeletedFalse(Long id);

    boolean existsByIdentityNumber(String identityNumber);

    Optional<Nasabah> findByIdentityNumberAndIsDeletedFalse(String identityNumber);

    List<Nasabah> findAllByIsDeletedFalse();

}
