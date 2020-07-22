package com.example.repository;

import com.example.entity.Entries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainEntryRepository extends JpaRepository<Entries, Long> {
}
