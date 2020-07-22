package com.example;

import com.example.entity.Entries;
import com.example.entity.Indicators;
import com.example.entity.Subdivisions;
import com.example.excel.ExcelPOIHelper;
import com.example.repository.IndicatorsRepository;
import com.example.repository.MainEntryRepository;
import com.example.repository.SubdivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class EntryParse {
    public ExcelPOIHelper excelPOIHelper;

    @Autowired
    private MainEntryRepository mainEntryRepository;

    @Autowired
    private SubdivisionRepository subdivisionRepository;

    @Autowired
    private IndicatorsRepository indicatorsRepository;

    public void parse(String fileLocation) throws IOException {
        excelPOIHelper = new ExcelPOIHelper();

        //Считывание первого листа
        Map<Integer, List<String>> data = excelPOIHelper.readExcel(fileLocation, 0);
        //Номера строк, которые нужно считать
        int[] nums = {10, 14, 19, 23, 24, 25, 26, 27, 28, 29, 31, 32, 33, 35, 36, 37, 38, 39, 41};
        String[] months = {"май", "июн", "июл", "авг", "сен", "окт", "ноя", "дек"};
        List<String> row = data.get(1);
        String date = row.get(14);

        for (int i : nums) {
            row = data.get(i);
            for (int j = 6; j < 14; j++) {
                Entries entries = new Entries(
                        months[j-6],
                        "2020",
                        row.get(j),
                        date,
                        excelPOIHelper.getAuthor());

                Subdivisions subdivisions = subdivisionRepository.findBySubdivisionname("АГЦ");
                entries.setSubdivisions(subdivisions);

                Indicators indicators = indicatorsRepository.findByIndicatorname(row.get(0));
                entries.setIndicators(indicators);

                mainEntryRepository.save(entries);
            }
        }

        //Считывание второго листа
        data = excelPOIHelper.readExcel(fileLocation, 1);
        //Номера строк, которые нужно считать
        nums = new int[]{7, 8, 9, 10, 11, 12, 13, 15, 16};

        for (int i : nums) {
            row = data.get(i);
            for (int j = 6; j < 14; j++) {
                Entries entries = new Entries(
                        months[j-6],
                        "2020",
                        row.get(j),
                        date,
                        excelPOIHelper.getAuthor());

                Subdivisions subdivisions = subdivisionRepository.findBySubdivisionname("ЦИБ");
                entries.setSubdivisions(subdivisions);

                Indicators indicators = indicatorsRepository.findByIndicatorname(row.get(0));
                entries.setIndicators(indicators);

                mainEntryRepository.save(entries);
            }
        }
    }

}
