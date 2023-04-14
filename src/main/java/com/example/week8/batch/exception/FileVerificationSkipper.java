package com.example.week8.batch.exception;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.text.ParseException;

@Component
public class FileVerificationSkipper implements SkipPolicy{

    @Override
    public boolean shouldSkip(Throwable exception, int skipCount) throws SkipLimitExceededException {
        System.out.println("여기가 바로 에러 처리 하는곳");
        System.out.println(exception);
        System.out.println(exception.getMessage());
        System.out.println("여기가 바로 에러 처리 하는곳");

        if(exception instanceof FileNotFoundException){
            return false;
        }else if(exception instanceof NullPointerException && skipCount <= 3){
            System.out.println("여긴 널포인트 익셉션");
            return true;
        }else if(exception instanceof FlatFileParseException &&skipCount <= 3){
            System.out.println("여긴 파스 오류");
            return true;
        }else{
            return false;
        }
    }
}
