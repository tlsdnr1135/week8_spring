package com.example.week8.batch.reader;

import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.file.LineCallbackHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLineCallbackHandle implements LineCallbackHandler {

    @Override
    public void handleLine(String line) {
        System.out.println("과연...");
        System.out.println(line);
        if (!line.equals("날짜,직접 광고 ID,노출수,클릭수,평균 노출순위,평균 클릭비용,광고비")) {
            throw new ItemStreamException("헤더 파싱 오류");
        }
    }
}