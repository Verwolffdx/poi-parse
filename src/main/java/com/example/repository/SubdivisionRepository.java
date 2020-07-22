package com.example.repository;

import com.example.entity.Subdivisions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubdivisionRepository extends JpaRepository<Subdivisions, Long> {
    Subdivisions findBySubdivisionname(String subdivisionname);

}
