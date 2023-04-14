package com.example.week8.batch.listener;

import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

@Component
public class CsvReaderListener implements ItemReadListener {

    @Override
    public void beforeRead() {
        
    }

    @Override
    public void afterRead(Object item) {

    }

    @Override
    public void onReadError(Exception exception) {
        System.out.println("여긴 리덜 에러 익셉션이다!!");
        System.out.println(exception);
    }
}
