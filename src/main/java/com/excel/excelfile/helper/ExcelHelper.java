package com.excel.excelfile.helper;

import com.excel.excelfile.entity.Wrestler;
import com.excel.excelfile.exception.ExcelFileException;
import com.excel.excelfile.exception.ExceptionTypes;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
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
            if(row.hasNext()){
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
}


