package com.example.week8.controller;

import com.example.week8.entity.DadDet;
import com.example.week8.repository.DaddetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TestApiController {

    private final DaddetRepository daddetRepository;


//    @GetMapping("/api/test")
//    public String saveDaddet(){
//        System.out.println("test");
//        DadDet daddet = DadDet.builder()
//                .adId(1L)
//                .build();
//        daddetRepository.save(daddet);
//        return "good";
//    }

}
