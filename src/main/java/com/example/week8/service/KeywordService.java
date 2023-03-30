package com.example.week8.service;

import com.example.week8.dto.ItemObject;
import com.example.week8.dto.kwd.find.KwdFindResDto;
import com.example.week8.dto.kwd.find.ResponseKeywordListJoinDaddet;
import com.example.week8.dto.kwd.find.ResponseKeywordListManualCnrKwdYn;
import com.example.week8.dto.kwd.save.KwdSaveReqDto;
import com.example.week8.dto.kwd.save.KwdSaveResDto;
import com.example.week8.entity.Kwd;
import com.example.week8.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

    //키워드 리스트 조회(키워드 검수, admin, ManualCnrKwdYn)
    public List<ResponseKeywordListManualCnrKwdYn> keywordListsManualCnrKwdYnFind(String keywordName){
        return keywordRepository.keywordListsManualCnrKwdYnFind(keywordName);
    }

    //키워드 리스트 업데이트(키워드 검수, admin, ManualCnrKwdYn)
    @Transactional
    public String keywordListsManualCnrKwdYnUpdate(String kwdName){
        Optional<Kwd> kwd = keywordRepository.findByKwdName(kwdName);
        if(kwd.isPresent()){ //해당 키워드가 디비에 존재하면
            if(kwd.get().getManualCnrKwdYn() == 1){ //존재하는 키워드의 수동 검사 여부가 1이면
                throw new RuntimeException("이미 수동 검사 여부가 1인 키워드 입니다.");
            }else{ //존재하는 키워드의 수동 검사 여부가 0이면
                kwd.get().setManualCnrKwdYn(1);
                return kwd.get().getKwdName();
            }
        }else{ //해당 키워드가 디비에 없으면
            Kwd entity = Kwd.builder()
                    .kwdName(kwdName)
                    .sellPossKwdYn(1)
                    .manualCnrKwdYn(1)
                    .build();
            keywordRepository.save(entity);
            return entity.getKwdName();
        }
    }
    
    //키워드 저장
    public KwdSaveResDto keywordSave(KwdSaveReqDto kwdSaveReqDto) {
        Kwd kwd = keywordRepository.save(kwdSaveReqDto.toFindKwd());
        return kwd.toKwdSaveResDto();
    }

    //검수 대상 키워드 삭제(키워드 검수, admin, ManualCnrKwdYn)
    @Transactional
    public String keywordListsManualCnrKwdYnOffUpdate(Long id) {
        Kwd kwd = keywordRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("아이디가 존재하지 않습니다. 삭제 불가!!")
        );
        kwd.setManualCnrKwdYn(0);
        return kwd.getKwdName();
    }
}
