package com.example.excel;

import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelPOIHelper {

    public Map<Integer, List<String>> readExcel(String fileLocation, Integer sheetNum) throws IOException {

        Map<Integer, List<String>> data = new HashMap<>();
        FileInputStream file = new FileInputStream(new File(fileLocation));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(sheetNum);
        int i = 0;
        for (Row row : sheet) {
            data.put(i, new ArrayList<String>());
            for (Cell cell : row) {
                switch (cell.getCellTypeEnum()) {
                case STRING:
                    data.get(i)
                        .add(cell.getStringCellValue());
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        data.get(i)
                            .add(cell.getDateCellValue() + "");
                    } else {
                        data.get(i)
                            .add((float)cell.getNumericCellValue() + "");
                    }
                    break;
                case BOOLEAN:
                    data.get(i)
                        .add(cell.getBooleanCellValue() + "");
                    break;
                case FORMULA:
                    FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                    data.get(i)
                        .add(evaluator.evaluate(cell).getNumberValue() + "");

                    // Print out value evaluated by formula
//                    System.out.print(evaluator.evaluate(cell).getNumberValue());
                    break;
                default:
                    data.get(i)
                        .add(" ");
                }
            }
            i++;
        }

//        String author = "user";
//        POIXMLProperties.ExtendedProperties expProps = getProperties().getExtendedProperties();
//        POIXMLProperties xmlProps = workbook.getProperties();
//        POIXMLProperties.CoreProperties coreProps =  xmlProps.getCoreProperties();
//        coreProps.setCreator(author);

        if (workbook != null){
            workbook.close();
        }

//        System.out.println("success");
        return data;
    }

    public String getAuthor() throws IOException {
        String fileLocation = "D:\\IDEAprojects\\POIparse\\src\\main\\resources\\Для алгоритма формирования баланса ЖРС (оцен. расходы АДП_май-дек 2020_03.04.2020).xlsx";

        FileInputStream file = new FileInputStream(new File(fileLocation));
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        POIXMLProperties xmlProps = workbook.getProperties();
        String author =  xmlProps.getCoreProperties().getCreator();

        return author;
    }

    public void writeExcel() throws IOException {
        Workbook workbook = new XSSFWorkbook();

        try {
            Sheet sheet = workbook.createSheet("Persons");
            sheet.setColumnWidth(0, 6000);
            sheet.setColumnWidth(1, 4000);

            Row header = sheet.createRow(0);

            CellStyle headerStyle = workbook.createCellStyle();

            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            XSSFFont font = ((XSSFWorkbook) workbook).createFont();
            font.setFontName("Arial");
            font.setFontHeightInPoints((short) 16);
            font.setBold(true);
            headerStyle.setFont(font);

            Cell headerCell = header.createCell(0);
            headerCell.setCellValue("Name");
            headerCell.setCellStyle(headerStyle);

            headerCell = header.createCell(1);
            headerCell.setCellValue("Age");
            headerCell.setCellStyle(headerStyle);

            CellStyle style = workbook.createCellStyle();
            style.setWrapText(true);

            Row row = sheet.createRow(2);
            Cell cell = row.createCell(0);
            cell.setCellValue("John Smith");
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue(20);
            cell.setCellStyle(style);

            File currDir = new File(".");
            String path = currDir.getAbsolutePath();
            String fileLocation = path.substring(0, path.length() - 1) + "temp.xlsx";

            FileOutputStream outputStream = new FileOutputStream(fileLocation);
            workbook.write(outputStream);
        } finally {
            if (workbook != null) {
               
                    workbook.close();
               
            }
        }
    }

}