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
    }

    @Override
    public void onWriteError(Exception exception, List items) {
    }
}
