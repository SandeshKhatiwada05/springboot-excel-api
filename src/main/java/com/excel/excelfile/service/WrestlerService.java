package com.excel.excelfile.service;

import com.excel.excelfile.entity.ExcelFile;
import com.excel.excelfile.entity.Wrestler;
import com.excel.excelfile.entity.dto.WrestlerDTO;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface WrestlerService {
    String save(ExcelFile file);
    List<WrestlerDTO> getAllWrestlers();
    ByteArrayInputStream downloadTemplate();
}
