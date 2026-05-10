package com.excel.excelfile.service;

import com.excel.excelfile.entity.ExcelFile;
import com.excel.excelfile.entity.Wrestler;

import java.util.List;

public interface WrestlerService {
    String save(ExcelFile file);
    List<Wrestler> getAllWrestlers();
}
