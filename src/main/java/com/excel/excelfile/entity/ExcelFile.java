package com.excel.excelfile.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExcelFile {
    @NotEmpty(message = "File cannot be empty")
    private MultipartFile excelFile;
}
