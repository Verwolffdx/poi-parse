package com.example.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Subdivisions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long subdivision_code;

    @NonNull
    private String subdivisionname;

    @OneToMany(mappedBy = "subdivisions")
    private Set<Entries> entries;

    @Override
    public String toString() {
        return "subdivision_name='" + subdivisionname + '\'';
    }
}
