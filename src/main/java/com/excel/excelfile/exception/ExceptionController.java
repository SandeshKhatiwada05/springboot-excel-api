package com.excel.excelfile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NoFileException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String noFile(NoFileException ex) {
        return "Exception " + ex;
    }

    @ExceptionHandler(ExcelFileException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String excelError(ExcelFileException ex) {
        return "Execption " + ex;
    }
}
