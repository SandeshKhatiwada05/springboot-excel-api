package com.excel.excelfile.controller;

import com.excel.excelfile.entity.ExcelFile;
import com.excel.excelfile.exception.ExceptionTypes;
import com.excel.excelfile.exception.NoFileException;
import com.excel.excelfile.service.WrestlerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wrestler")
public class WrestlerController {
    private final WrestlerService wrestlerService;

    @PostMapping("/save-via-excel")
    public String addWrestlerViaExcel(@ModelAttribute ExcelFile file){
        return wrestlerService.save(file);
    }
}
