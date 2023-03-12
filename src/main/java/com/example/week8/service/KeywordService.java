package com.example.week8.service;

import com.example.week8.dto.ItemObject;
import com.example.week8.entity.Kwd;
import com.example.week8.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeywordService {

    private final KeywordRepository keywordRepository;

    //키워드 조회
    public ItemObject keywordFind() {
        ItemObject itemObject = new ItemObject();
        itemObject.setKwds(keywordRepository.findAll());
        return itemObject;
    }
    
    //키워드 저장
    public Kwd keywordSave(Kwd kwd) {
        kwd.setSellPossKwdYn(1); //true
        kwd.setManualCnrKwdYn(0); //false
        return keywordRepository.save(kwd);
    }
}
