package com.example.directory;

import com.example.entity.Subdivisions;
import com.example.repository.SubdivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SubdivisionDirectory {
    @Autowired
    private SubdivisionRepository subdivisionRepository;

    public void readSubdivision() {
        Subdivisions subdivision1 = new Subdivisions("АГЦ");
        Subdivisions subdivision2 = new Subdivisions("ЦИБ");

        List<Subdivisions> subdivisions = Arrays.asList(subdivision1, subdivision2);
        subdivisionRepository.saveAll(subdivisions);
    }
}
