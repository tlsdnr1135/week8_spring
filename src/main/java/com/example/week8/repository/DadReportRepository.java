package com.example.week8.repository;

import com.example.week8.entity.DadReport;
import com.example.week8.entity.DadReportPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DadReportRepository extends JpaRepository<DadReport, DadReportPK> {

    Optional<DadReport> findByRequestDateAndDadDetId(String requestDate, Long dadDetId);


}
