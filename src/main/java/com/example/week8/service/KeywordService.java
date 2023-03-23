package com.example.week8.service;

import com.example.week8.dto.ItemObject;
import com.example.week8.dto.kwd.find.KwdFindResDto;
import com.example.week8.dto.kwd.find.ResponseKeywordListJoinDaddet;
import com.example.week8.dto.kwd.save.KwdSaveReqDto;
import com.example.week8.dto.kwd.save.KwdSaveResDto;
import com.example.week8.entity.Kwd;
import com.example.week8.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KeywordService {

    private final KeywordRepository keywordRepository;

    //키워드 조회
    public List<KwdFindResDto> keywordFind() {
        return keywordRepository.findAll().stream().map(s -> s.toKwdFindResDto()).collect(Collectors.toList());
    }
    //키워도 조회 join daddet
    public List<ResponseKeywordListJoinDaddet> keywordListJoinDadDetFind(Integer adId,String kwdName){
        System.out.println(adId);
        return keywordRepository.keywordListJoinDadDetFind(adId,kwdName);
    }
    
    //키워드 저장
    public KwdSaveResDto keywordSave(KwdSaveReqDto kwdSaveReqDto) {
        Kwd kwd = keywordRepository.save(kwdSaveReqDto.toFindKwd());
        return kwd.toKwdSaveResDto();
    }
}
