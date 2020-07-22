package com.example.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Entries {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long entry_code;

    @ManyToOne
    @JoinColumn(name="subdivision_code")
    private Subdivisions subdivisions;

    @ManyToOne
    @JoinColumn(name="indicator_code")
    private Indicators indicators;

    @NonNull
    private String month;

    @NonNull
    private String year;

    @NonNull
    private String index_value;

    @NonNull
    private String date_of_input;

    @NonNull
    private String username;

    @Override
    public String toString() {
        return "Entries\n" +
                "entrycode=" + entry_code + "\n" +
                "subdivisions=" + subdivisions.toString() + "\n" +
                "indicators=" + indicators.toString() + "\n" +
                "month='" + month + '\'' + "\n" +
                "year='" + year + '\'' + "\n" +
                "index_value=" + index_value + "\n" +
                "date_of_input='" + date_of_input + '\'' + "\n" +
                "username='" + username + '\'' + "\n";
    }
}
