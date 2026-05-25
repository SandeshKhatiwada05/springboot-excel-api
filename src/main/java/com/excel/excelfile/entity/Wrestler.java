package com.excel.excelfile.entity;

import com.excel.excelfile.annotation.ExcelExport;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Wrestler {
    @Id
    @SequenceGenerator(name = "wrestler_gen", sequenceName = "wrestler_Seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wrestler_gen")
    private Integer id;

    @ExcelExport(headerName = "name", orderNumber = 1)
    private String name;

    @ExcelExport(headerName = "finisher", orderNumber = 2)
    private String finisher;

    @ExcelExport(headerName = "isActive", orderNumber = 3)
    private boolean isActive;
}
