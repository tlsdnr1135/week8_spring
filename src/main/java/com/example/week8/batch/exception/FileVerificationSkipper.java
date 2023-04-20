package com.example.week8.batch.exception;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;


@Component
public class FileVerificationSkipper implements SkipPolicy{

    @Value("${file.bacthPath}")
    private String ERROR_PATH;

    @Override
    public boolean shouldSkip(Throwable exception, int skipCount) throws SkipLimitExceededException {
        try {
            // 1. 파일 객체 불러오기
//            FileReader file = new FileReader("C:\\java_workspace\\IntelliJ_workspace\\11h11m\\week8_spring\\src\\main\\resources\\batcherror\\batchError.txt"); //집
            FileReader file = new FileReader(ERROR_PATH+"/batchError.txt"); //회사

            // 2. Buffer를 사용해서 File에 write할 수 있는 BufferedWriter 생성
            BufferedReader  br = new BufferedReader (file);

            String result="";
            String temp = "";
            while((result = br.readLine()) != null) {
                temp += result;
                temp += "\n";
            }
            br.close();
            System.out.println("잘 불러져 왔는지 확인할래 : " + temp);

//            FileWriter fw = new FileWriter("C:\\java_workspace\\IntelliJ_workspace\\11h11m\\week8_spring\\src\\main\\resources\\batcherror\\batchError.txt"); //집
            FileWriter fw = new FileWriter(ERROR_PATH+"/batchError.txt"); //회사
            BufferedWriter bw = new BufferedWriter(fw);

            // 3. 파일에 쓰기
            bw.write(temp);
            bw.write(exception.getMessage());
            bw.write("\n");
            bw.flush();

            // 4. BufferedWriter close
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        if(exception instanceof FileNotFoundException){
            return false;
        }else if(exception instanceof NullPointerException && skipCount <= 100){
            System.out.println("여긴 널포인트 익셉션");
            return true;
        }else if(exception instanceof FlatFileParseException && skipCount <= 100){
            System.out.println("여긴 파스 오류");
            return true;
        }else{
            return false;
        }
    }
}
