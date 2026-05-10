package com.excel.excelfile.entity.dto;

import com.excel.excelfile.entity.Wrestler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WrestlerDTO {
    private String name;
    private String finisher;

    public WrestlerDTO(Wrestler wrestler) {
        this.name = wrestler.getName();
        this.finisher = wrestler.getFinisher();
    }
}
