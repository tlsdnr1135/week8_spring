package com.example.week8.service;

import com.example.week8.dto.AdDto;
import com.example.week8.dto.KwdDto;
import com.example.week8.dto.ad.update.RequestAdActYnAllDto;
import com.example.week8.dto.ad.update.ResponseCurrentStateAdListDto;
import com.example.week8.entity.*;
import com.example.week8.enums.AccountRoleEnum;
import com.example.week8.repository.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
//@Transactional
public class AdServiceTest {

//    @Mock
//    private AgroupRepository agroupRepository;
//
//    @Mock
//    private AdvRepository advRepository;
//
//    @Mock
//    private ItemRepository itemRepository;

//    @Mock
//    private AdRepository mockARepository;

//    @Mock
//    private KeywordRepository keywordRepository;
//
//    @Mock
//    private DaddetRepository daddetRepository;
//
//    @Mock
//    private DaddetbidRepository daddetbidRepository;
//
//    @Mock
//    private CnrReqRepository cnrReqRepository;

    @Autowired
    private AdRepository adRepository;
    @Autowired
    private  DaddetRepository daddetRepository;
    @Autowired
    private AdvRepository advRepository;
    @Autowired
    private AgroupRepository agroupRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private AdService  adService;

//


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
        //광고주 아이디 가져오기
//        when(advRepository.findById(adDto.getAdv().getName()).get()).thenReturn();



        //when
        Object str = adService.saveAd(adDto);

        //then



    }

    @Test
    @DisplayName("updateAdActYnALl")
    @Transactional
    public void updateAdActYnALl(){
        
        //given - dto
        List<Long> longList = new ArrayList<>();
        longList.add(3L);
        Integer integer = 0;

        RequestAdActYnAllDto dto = new RequestAdActYnAllDto();
        dto.setLongList(longList);
        dto.setYn(integer);

        //stub - ad, DaddetReport 만들기
        Adv adv = Adv.builder()
                .name("adv")
                .password("1")
                .role(AccountRoleEnum.valueOf("ROLE_ADV"))
                .adIngActYn(1)
                .balance(1000L)
                .eventMoneyBalance(0L)
                .dayLimitBudget(0L)
                .build();
        adv.encodePassword(passwordEncoder);
        advRepository.saveAndFlush(adv); //TODO 여기에 플러시를 왜 붙여야만 하는가?

        Agroup agroup = Agroup.builder()
                .agroupName("설빈그룹")
                .agroupActYn(1)
                .agroupUseActYn(1)
                .build();
        agroupRepository.save(agroup);

        Item item = Item.builder()
                .itemNo("상품번호01")
                .itemName("상품명")
                .adultYn(1)
                .itemOrgCost(1000L)
                .itemActYn(1)
                .build();
        itemRepository.save(item);

        Ad ad = Ad.builder()
                .adv(adv)
                .agroup(agroup)
                .item(item)
                .adActYn(1)
                .adUseConfigYn(1)
                .build();
        System.out.println(ad.getAdv().getName());
        adRepository.save(ad);

        /***************************************/
        Kwd kwd = Kwd.builder()
                .kwdName("설빈키워드")
                .sellPossKwdYn(1)
                .manualCnrKwdYn(1)
                .build();
        keywordRepository.save(kwd);

        DadDet dadDet = DadDet.builder()
                .ad(ad)
                .kwd(kwd)
                .dadCnr("APPROVAL")
                .cnrReqId(null)
                .dadActYn(1)
                .dadUseConfigYn(1)
                .build();
        daddetRepository.save(dadDet);

        System.out.println("광고 act 전 : " + ad.getAdActYn());
        System.out.println("DadDet : " + dadDet.getId());
        System.out.println("DadDet act 전 : " + dadDet.getDadActYn());



        //when
//        entityManager.flush();
//        entityManager.clear();
        adService.updateAdActYnALl(dto);

        //then
        List<Ad> adList = adRepository.findAll();
        List<DadDet> dadDetList = daddetRepository.findAll();

        assertEquals(1,adList.size());
        assertEquals(1,dadDetList.size());
        assertEquals(0,adList.get(0).getAdActYn());
        assertEquals(0,adList.get(0).getAdUseConfigYn());
        assertEquals(0,dadDetList.get(0).getDadActYn());
        assertEquals(1,dadDetList.get(0).getDadUseConfigYn());

    }
//

    /**
     * 이건 mock으로 한 것
     */
//    @Test
//    @DisplayName("findCurrentStateAdLists")
//    public void findCurrentStateAdLists(){
//
//        //given
//        List<ResponseCurrentStateAdListDto> lists = new ArrayList<>();
//        ResponseCurrentStateAdListDtoImpl dto = ResponseCurrentStateAdListDtoImpl.builder()
//                .key(1L)
//                .itemName("sulbin")
//                .kwdName("키워드")
//                .adultYn(1)
//                .build();
//        lists.add(dto);
//
//        //stub
//        when(adRepository.findCurrentStateAdLists()).thenReturn(lists);
//
//        //when
//        List<ResponseCurrentStateAdListDto> currentStateAdLists = adService.findCurrentStateAdLists();
//
//        //then
//        assertEquals(1,currentStateAdLists.size());
//        assertEquals(1L,currentStateAdLists.get(0).getKey());
//        assertEquals("sulbin",currentStateAdLists.get(0).getItemName());
//        assertEquals("키워드",currentStateAdLists.get(0).getKwdName());
//        assertEquals(1,currentStateAdLists.get(0).getAdultYn());
//
//    }

    /**
     * 이건 mock으로 하려다 실패한 것
     */
//    @Test
//    @DisplayName("updateAdActYnALl")
//    public void updateAdActYnALl(){
//        //given - dto
//        List<Long> longList = new ArrayList<>();
//        longList.add(1L);
//        Integer integer = 0;
//
//        RequestAdActYnAllDto dto = new RequestAdActYnAllDto();
//        dto.setLongList(longList);
//        dto.setYn(integer);
//
//        //stub - ad, DaddetReport 만들기
//        Ad ad = Ad.builder()
//                .id(1L)
//                .adActYn(1)
//                .build();
//
//        //이거를 어떻게 검증하지..?
//        List<Ad> mock = mock(List.class);
//        mock.add(ad);
//
//
//
////        adRepository.updateAdActYnALl(dto.getLongList(),dto.getYn());
////        daddetRepository.updateDadActYnAll(longList,integer);
//
//        //when
//        adService.updateAdActYnALl(dto);
//
//        //then
////        verify(mock).add(captor.capture());
////        assertEquals(0,ad.getAdActYn());
//        System.out.println("-------------------");
//        System.out.println("아이디 : " + ad.getId());
//        System.out.println("adAct : " + ad.getAdActYn());
//
//    }

    /**
     * 디티오 클래스
     */
    @Getter
    @Setter
    @NoArgsConstructor
    private static class ResponseCurrentStateAdListDtoImpl implements ResponseCurrentStateAdListDto{

        private Long key;
        private String itemName;
        private String kwdName;
        private Integer adultYn;

        @Override
        public Long getKey() {
            return this.key;
        }

        @Override
        public String getItemName() {
            return this.itemName;
        }

        @Override
        public String getKwdName() {
            return this.kwdName;
        }

        @Override
        public Integer getAdultYn() {
            return this.adultYn;
        }

        @Builder
        public ResponseCurrentStateAdListDtoImpl(Long key, String itemName, String kwdName, Integer adultYn) {
            this.key = key;
            this.itemName = itemName;
            this.kwdName = kwdName;
            this.adultYn = adultYn;
        }
    }

}
