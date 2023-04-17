package com.example.week8.repository;

import com.example.week8.entity.DadReport;
import com.example.week8.entity.TaskRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class DadReportRepositoryTest {

    @Autowired
    DadReportRepository dadReportRepository;

    /**
     * save
     **/
    @Test
    @DisplayName("성공_save_테스트")
    public void save_Success() {
        //given
        String taskName = "남신욱";
        DadReport dadReport1 = DadReport.builder()
                .advId("admin")
                .requestDate("20230417")
                .dadDetId(1L)
                .showCount(100L)
                .build();
        DadReport dadReport2 = DadReport.builder()
                .advId("admin")
                .requestDate("20230418")
                .dadDetId(1L)
                .showCount(100L)
                .build();

        //when
        dadReportRepository.save(dadReport1);
        dadReportRepository.save(dadReport2);

        //then
        List<DadReport> dadReportList = dadReportRepository.findByDadDetId(dadReport1.getDadDetId());

        assertEquals(2, dadReportList.size());
    }

}
