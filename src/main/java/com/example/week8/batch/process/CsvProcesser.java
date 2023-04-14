package com.example.week8.batch.process;

import com.example.week8.entity.DadDet;
import com.example.week8.entity.DadReport;
import com.example.week8.entity.csv.DadReportCsv;
import com.example.week8.repository.DadReportRepository;
import com.example.week8.repository.DaddetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//@StepScope
@Configuration
@RequiredArgsConstructor
public class CsvProcesser implements ItemProcessor<DadReportCsv, DadReport> {


//    @Value("#{jobParameters[date]}")
//    private String date;
    private final DadReportRepository dadReportRepository;
    private final DaddetRepository daddetRepository;


    @Override
    @Transactional
    public DadReport process( DadReportCsv item) throws Exception {
        System.out.println(" -------------------------------------- 여기가 배치 프로세서 --------------------------------------");
        System.out.println("날짜 " + item.getRequestDate());
        System.out.println("직접 광고  Id : " + item.getDadDetId());
        
        //널 체크
        item.checkNull();

        //여기서 중복 날짜 아이디
        Optional<DadReport> dadReportOp2 =  dadReportRepository.findByRequestDateAndDadDetId(item.getRequestDate(), item.getDadDetId());
        if(dadReportOp2.isPresent()) {//중복 있으면
            System.out.println("중복 테스트 " + dadReportOp2.get().getRequestDate());
            DadReport dadReportDuplication = dadReportOp2.get().isDadReportDuplication(item);
            //리턴을 안해주어도 될듯한데...
//            return dadReportOp2.get();
            return dadReportDuplication;
        }else{ //중복 없으면
            //광고주 아이디
            DadDet dadDet = daddetRepository.findById(item.getDadDetId()).get();
            System.out.println("광고주 아이디 : "  +  dadDet.getAd().getAdv().getName());

            DadReport dadReport = DadReport.builder()
                    .advId(dadDet.getAd().getAdv().getName())
                    .requestDate(item.getRequestDate())
                    .dadDetId(item.getDadDetId())
                    .showCount(item.getShowCount())
                    .clickCount(item.getClickCount())
                    .avgShowRank(item.getAvgShowRank())
                    .avgCpc(item.getAvgCpc())
                    .adCost(item.getAdCost())
                    .build();
            return dadReport;
        }

    }
}
