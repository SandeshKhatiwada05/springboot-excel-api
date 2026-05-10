package com.excel.excelfile.repository;

import com.excel.excelfile.entity.Wrestler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WrestlerRepository extends JpaRepository<Wrestler, Integer> {
}
