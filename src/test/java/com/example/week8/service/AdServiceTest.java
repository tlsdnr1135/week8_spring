package com.example.week8.service;

import com.example.week8.dto.AdDto;
import com.example.week8.dto.KwdDto;
import com.example.week8.entity.Adv;
import com.example.week8.entity.Agroup;
import com.example.week8.entity.Item;
import com.example.week8.entity.Kwd;
import com.example.week8.repository.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AdServiceTest {

    @Mock
    private AgroupRepository agroupRepository;

    @Mock
    private AdvRepository advRepository;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private AdRepository adRepository;

    @Mock
    private KeywordRepository keywordRepository;

    @Mock
    private DaddetRepository daddetRepository;

    @Mock
    private DaddetbidRepository daddetbidRepository;

    @Mock
    private CnrReqRepository cnrReqRepository;


    @InjectMocks
    private AdService  adService;

    @Test
    @DisplayName("광고 저장")
    public void saveAd(){

        //given
        //키워드 생성
        List<KwdDto> kwds = new ArrayList<>();
        kwds.add(KwdDto.builder().kwdName("키워드1").build());
        kwds.add(KwdDto.builder().kwdName("키워드2").build());

        //DTO
        AdDto adDto = AdDto.builder()
                .adv(Adv.builder().name("설빈").build())
                .agroup(Agroup.builder().agroupName("설빈광고").build())
                .item(Item.builder().id(1L).build())
                .kwd(kwds)
                .build();

        //stub



        //when


        //then


    }

}
