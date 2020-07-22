package com.example.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Indicators {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int indicator_code;

    @NonNull
    private String indicatorname;

    @OneToMany(mappedBy = "indicators")
    private Set<Entries> entries;

    @Override
    public String toString() {
        return "indicator_name='" + indicatorname + '\'';
    }
}
