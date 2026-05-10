package com.excel.excelfile.entity;

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

    private String name;

    private String finisher;
}
