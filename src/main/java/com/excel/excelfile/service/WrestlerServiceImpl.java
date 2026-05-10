package com.excel.excelfile.service;

import com.excel.excelfile.entity.ExcelFile;
import com.excel.excelfile.entity.Wrestler;
import com.excel.excelfile.exception.ExcelFileException;
import com.excel.excelfile.exception.ExceptionTypes;
import com.excel.excelfile.helper.ExcelHelper;
import com.excel.excelfile.repository.WrestlerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WrestlerServiceImpl implements WrestlerService {

    private final WrestlerRepository wrestlerRepository;
    private final ExcelHelper excelHelper;

    @Override
    public String save(ExcelFile excelFile) {
        MultipartFile file = excelFile.getExcelFile();
        if (excelHelper.isExcel(file)) {
            try {
                List<Wrestler> wrestlers = excelHelper.excelToWrestlerEntity(file.getInputStream());
                wrestlerRepository.saveAll(wrestlers);
                return "Wrestlers saved";
            } catch (IOException e) {
                throw new ExcelFileException(ExceptionTypes.EXCEL_ERROR);
            }
        }
        return ExceptionTypes.NON_EXCEL_FILE;
    }

    @Override
    public List<Wrestler> getAllWrestlers() {
        return wrestlerRepository.findAll();
    }
}
