package com.example.week8.batch.listener;

import com.example.week8.entity.csv.DadReportCsv;
import com.example.week8.repository.TaskRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class CsvWriteListener implements ItemWriteListener {
    
    private final TaskRequestRepository taskRequestRepository;
    
    //클래스로 상속.
    @Override
    public void beforeWrite(List items) {

    }

    //
    @Override
    public void afterWrite(List items) {
        System.out.println("여기는 프로세서 에서 넘긴 아이템");
        //스코프에서 잡 파라미터 가져오기
        List<DadReportCsv> dadReportCsvs = items;
//        taskRequestRepository.findByTaskName(dadReportCsvs.get(0).get);
        System.out.println("정상 처리 됐으니까 내가 여기서 COMPLETE로 넣어줄게");
    }

    @Override
    public void onWriteError(Exception exception, List items) {
        System.out.println("오류 났으니까 내가 여기서 ERROR로 넣어줄게");
    }
}
