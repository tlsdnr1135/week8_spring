package com.example.week8.batch.writer;

import com.example.week8.entity.DadReport;
import com.example.week8.repository.DadReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomItemWriter<T> implements ItemWriter {
    private final DadReportRepository dadReportRepository;

    @Override
    public void write(List items) throws Exception {
        System.out.println(" -------------------------------------------------------------------------");
        System.out.println("나는 아이템 라이터");
        for(DadReport dadReport : (List<DadReport>) items){
            System.out.println("직접 광고 상세 아이디 "+dadReport.getDadDetId());
            System.out.println("광고주 아이디 "+dadReport.getAdvId());
            System.out.println("날짜 "+dadReport.getRequestDate());
            System.out.println("클릭 카운트 "+dadReport.getClickCount());
            System.out.println("쇼 카운트 "+dadReport.getShowCount());
            System.out.println("광고비 "+dadReport.getAdCost());
            System.out.println("평균 클릭수 "+dadReport.getAvgCpc());
            System.out.println("평균 노출 랭크 "+dadReport.getAvgShowRank());
            System.out.println(" -------------------------------------------------------------------------");
        }
        dadReportRepository.saveAll(items);
    }

}
