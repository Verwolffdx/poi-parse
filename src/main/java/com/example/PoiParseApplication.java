package com.example;

import com.example.directory.IndicatorsDirectory;
import com.example.directory.SubdivisionDirectory;
import com.example.entity.Entries;
import com.example.entity.Indicators;
import com.example.entity.Subdivisions;
import com.example.excel.ExcelPOIHelper;
import com.example.repository.IndicatorsRepository;
import com.example.repository.MainEntryRepository;
import com.example.repository.SubdivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class PoiParseApplication implements CommandLineRunner {

    private ExcelPOIHelper excelPOIHelper;

    @Autowired
    private IndicatorsDirectory indicatorsDirectory;

    @Autowired
    private SubdivisionDirectory subdivisionDirectory;

    @Autowired
    private EntryParse entryParse;

    @Autowired
    private MainEntryRepository mainEntryRepository;

    @Autowired
    private IndicatorsRepository indicatorsRepository;

    @Autowired
    private SubdivisionRepository subdivisionRepository;

    public static void main(String[] args) {
        SpringApplication.run(PoiParseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String fileLocation = "D:\\IDEAprojects\\POIparse\\src\\main\\resources\\Для алгоритма формирования баланса ЖРС (оцен. расходы АДП_май-дек 2020_03.04.2020).xlsx";

        excelPOIHelper = new ExcelPOIHelper();

        System.out.println("Дождитесь окончание парсинга...");
        //Создание справочника показателей
        indicatorsDirectory.readIndicators(fileLocation);
        //Создание справочника подразделений
        subdivisionDirectory.readSubdivision();

        //Парсинг файла и запись в БД
        entryParse.parse(fileLocation);

        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите таблицу (введиет число):\n" +
                    "1. Entries\n" +
                    "2. Subdivisions\n" +
                    "3. Indicators\n" +
                    "0. Выход");

            int tableName = scan.nextInt();

            switch (tableName) {
                case 0:
                    System.exit(0);
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Ошибка");
                    continue;
            }

            while (true) {
                System.out.println("Выберите действие:\n" +
                        "1. Вывод всех записей таблицы\n" +
                        "2. Назад\n" +
                        "0. Выход");

                int action = scan.nextInt();

                if (action == 2) {
                    break;
                }

                switch (action) {
                    case 0:
                        System.exit(0);
                    case 1:
                        printTable(tableName);
                        break;
                    default:
                        System.out.println("Ошибка");
                        break;
                }
            }
        }
    }

    public void printTable(int tableName) {
        switch (tableName) {
            case 1:
                printEntries();
                break;
            case 2:
                printSubdivisions();
                break;
            case 3:
                printIndicators();
                break;
        }

    }

    public void printEntries() {
        //Вывод таблицы Entries
        Iterable<Entries> allEntry = mainEntryRepository.findAll();
        for (Entries entry : allEntry) {
            System.out.println(entry);
        }
    }

    public void printSubdivisions() {
        //Вывод таблицы Subdivisions
        Iterable<Subdivisions> allSubdivisions = subdivisionRepository.findAll();
        for (Subdivisions subdivision : allSubdivisions) {
            System.out.println(subdivision);
        }
    }

    public void printIndicators() {
        //Вывод таблицы Indicators
        Iterable<Indicators> allIndicators = indicatorsRepository.findAll();
        for (Indicators indicator : allIndicators) {
            System.out.println(indicator);
        }
    }
}
