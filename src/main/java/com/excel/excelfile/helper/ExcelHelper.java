package com.excel.excelfile.helper;

import com.excel.excelfile.annotation.ExcelExport;
import com.excel.excelfile.entity.Wrestler;
import com.excel.excelfile.exception.ExcelFileException;
import com.excel.excelfile.exception.ExceptionTypes;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.IndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@Configuration
@Slf4j
public class ExcelHelper {

    //check if Excel is empty
    public boolean isExcel(MultipartFile file) {
        if (file.isEmpty()) {
            log.info("Error {}", ExceptionTypes.N0_FILE_EXISTS);
            return false;
        }

        String contentType = file.getContentType();
        assert contentType != null;
        return (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
    }

    //convert Excel to list
    public List<Wrestler> excelToWrestlerEntity(InputStream inStream) {
        List<Wrestler> wrestlerList = new ArrayList<>();
        try {

            XSSFWorkbook workBook = new XSSFWorkbook(inStream);
            XSSFSheet sheet = workBook.getSheetAt(0);

            Iterator<Row> row = sheet.iterator();
            if (row.hasNext()) {
                row.next(); //skipping header
            }

            while (row.hasNext()) {
                Row currentRow = row.next();

                //initialize the entity
                Wrestler wrestler = new Wrestler();

                Iterator<Cell> cells = currentRow.iterator();
                int cellId = 0;

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cellId) {
                        case 0 -> wrestler.setName(cell.getStringCellValue());
                        case 1 -> wrestler.setFinisher(cell.getStringCellValue());
                        default -> log.info("Default");
                    }
                    cellId++;
                }
                wrestlerList.add(wrestler);
            }

        } catch (Exception e) {
            throw new ExcelFileException(ExceptionTypes.EXCEL_ERROR);
        }
        return wrestlerList;
    }

    public ByteArrayInputStream downloadTemplate() {
        try (XSSFWorkbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream();) {

            XSSFSheet sheet = workbook.createSheet("wrestlers");
            sheet.createFreezePane(0, 1);

            //set cell style
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setWrapText(true);
            cellStyle.setFillForegroundColor(IndexedColors.BLUE1.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            Row header = sheet.createRow(0);
            header.createCell(0, CellType.STRING).setCellValue("name");
            header.createCell(1, CellType.STRING).setCellValue("finisher");
            header.setRowStyle(cellStyle);


            //size
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);

            workbook.write(out);

            return new ByteArrayInputStream(out.toByteArray());

        } catch (Exception e) {
            throw new ExcelFileException(ExceptionTypes.TEMPLATE_ISSUE);
        }
    }

    public ByteArrayInputStream downloadGenericTemplate(Class<?> clazz) {
        try (XSSFWorkbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            String sheetName = clazz.getSimpleName();
            XSSFSheet sheet = workbook.createSheet(sheetName);
            sheet.createFreezePane(0, 1);

            Row headerRow = sheet.createRow(0);

            //set cell style
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setWrapText(true);
            cellStyle.setFillForegroundColor(IndexedColors.BLUE1.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            Font font = workbook.createFont();
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            cellStyle.setFont(font);

            List<Field> field = List.of(clazz.getDeclaredFields());
            List<String> annotatedField = new ArrayList<>();
            for (Field singleField : field) {
                if (singleField.isAnnotationPresent(ExcelExport.class)) {
                    log.info("Header :{}", singleField);
                    annotatedField.add(singleField.getName());
                }
            }

            int currentColumn = 0;
            for (String column : annotatedField) {
                Cell cell = headerRow.createCell(currentColumn);
                cell.setCellStyle(cellStyle);

                sheet.setColumnWidth(currentColumn, 20 * 265);
                cell.setCellValue(column);
                currentColumn++;
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}


