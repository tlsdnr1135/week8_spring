package com.example.week8.batch.writer;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomItemWriter<T> implements ItemWriter {
    private final EntityManagerFactory entityManagerFactory;

    @Override
    public void write(List items) throws Exception {
        System.out.println("나는 아이템 라이터");
    }

    //    @Override
//    public void write(List<? extends T> items) {
//        System.out.println("나는 아이템 라이터");
//
//    }
}
