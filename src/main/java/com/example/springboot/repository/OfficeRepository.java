package com.example.springboot.repository;

import com.example.springboot.entity.Office;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfficeRepository extends JpaRepository<Office, Long> {
    Optional<Office> findOfficeByOfficeName(String officeName);
}
