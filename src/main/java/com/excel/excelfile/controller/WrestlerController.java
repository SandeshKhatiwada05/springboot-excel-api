package com.excel.excelfile.controller;

import com.excel.excelfile.entity.ExcelFile;
import com.excel.excelfile.entity.Wrestler;
import com.excel.excelfile.entity.dto.WrestlerDTO;
import com.excel.excelfile.service.WrestlerService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wrestler")
public class WrestlerController {
    private final WrestlerService wrestlerService;

    @PostMapping("/save-via-excel")
    public ResponseEntity<String> addWrestlerViaExcel(@ModelAttribute ExcelFile file) {
        String wrestler = wrestlerService.save(file);
        return new ResponseEntity<>(wrestler, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<WrestlerDTO>> getAllWrestlers() {
        return new ResponseEntity<>(wrestlerService.getAllWrestlers(), HttpStatus.OK);
    }

    @GetMapping("/download-template-old")
    public ResponseEntity<InputStreamResource> downloadTemplate(){
        String fileName = "wrestler-template.xlsx";

        ByteArrayInputStream stream =
                wrestlerService.downloadTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + fileName);

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(stream));
    }

    @GetMapping("/download-template")
    public ResponseEntity<InputStreamResource> downloadWrestlerTemplate(){
        String fileName = "wrestler-template.xlsx";

        ByteArrayInputStream stream =
                wrestlerService.downloadGenericTemplate(Wrestler.class);

        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + fileName);

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(stream));

    }
}
