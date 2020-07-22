package com.example.directory;

import com.example.entity.Indicators;
import com.example.entity.Subdivisions;
import com.example.excel.ExcelPOIHelper;
import com.example.repository.IndicatorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.*;

@Component
public class IndicatorsDirectory {

    @Autowired
    private IndicatorsRepository indicatorsRepository;

    public ExcelPOIHelper excelPOIHelper;

    public void readIndicators(String fileLocation) throws IOException { ;

        excelPOIHelper = new ExcelPOIHelper();

        //Считывание первого листа
        Map<Integer, List<String>> data = excelPOIHelper.readExcel(fileLocation, 0);
        //Номера строк, которые нужно считать
        int[] nums = {10, 14, 19, 23, 24, 25, 26, 27, 28, 29, 31, 32, 33, 35, 36, 37, 38, 39, 41};
        for (int i : nums) {
            List<String> row = data.get(i);
            Indicators indicators = new Indicators(row.get(0).toString());
            indicatorsRepository.save(indicators);
        }

        //Считывание второго листа
        data = excelPOIHelper.readExcel(fileLocation, 1);
        //Номера строк, которые нужно считать
        nums = new int[]{7, 8, 9, 10, 11, 12, 13, 15, 16};
        for (int i : nums) {
            List<String> row = data.get(i);
            Indicators indicators = new Indicators(row.get(0).toString());
            indicatorsRepository.save(indicators);
        }
    }
}
