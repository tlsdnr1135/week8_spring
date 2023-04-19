package com.example.week8.service;

import com.example.week8.dto.AdDto;
import com.example.week8.dto.KwdDto;
import com.example.week8.dto.ad.update.RequestAdActYnAllDto;
import com.example.week8.dto.ad.update.RequestAdUseConfigYnAllDto;
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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AdServiceTest {


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
    private CnrReqRepository cnrReqRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private AdService  adService;


    @Test
    @DisplayName("광고_저장_수동_검수_키워드_여부_False_일때")
    @Transactional
    public void saveAd(){

        //given
        Adv adv = createAdv();
        advRepository.saveAndFlush(adv);

        Item item = createItem();
        itemRepository.save(item);

        //DTO
        List<KwdDto> kwdDtos = new ArrayList<>();
        kwdDtos.add(KwdDto.builder().kwdName(adv.getName()).bidCost(1000L).build());

        AdDto adDto = AdDto.builder()
                .adv(Adv.builder().name("adv").build())
                .agroup(Agroup.builder().agroupName("설빈광고").build())
                .item(Item.builder().id(item.getId()).build())
                .kwd(kwdDtos) //리스트 타입
                .build();
        
//        Ad ad = createAd();

        //when
        String returnMessage = adService.saveAd(adDto);

        //then
        List<Ad> adLists = adRepository.findAll();

        assertEquals("성공",returnMessage);
        assertEquals(1, adLists.size());



    }

//sdsdds새롭게 커밋하자
    @Test
    @DisplayName("findAll")
    @Transactional
    void findAll(){
        //given
        Ad ad = createAd();
        adRepository.save(ad);

        //when
        List<Ad> adList = adService.findAll();

        //then
        assertEquals(1,adList.size());
        assertEquals("설빈그룹",adList.get(0).getAgroup().getAgroupName());

    }

    @Test
    @DisplayName("updateAdUseConfigYnAll")
    void updateAdUseConfigYnAll(){

        //given
        List<Long> longList = new ArrayList<>();
        longList.add(3L);
        Integer integer = 0;

        RequestAdUseConfigYnAllDto dto = new RequestAdUseConfigYnAllDto();
        dto.setLongList(longList);
        dto.setYn(integer);

        Ad ad = createAd();
        adRepository.save(ad);

        DadDet dadDet = createDadDet(ad);
        daddetRepository.save(dadDet);

        //when
        adService.updateAdUseConfigYnALl(dto);

        //then
        List<Ad> adList = adRepository.findAll();
        List<DadDet> dadDetList = daddetRepository.findAll();

        assertEquals(1,adList.size());
        assertEquals(1,dadDetList.size());
        assertEquals(0,adList.get(0).getAdUseConfigYn());
        assertEquals(0,dadDetList.get(0).getDadUseConfigYn());
        assertEquals(1,adList.get(0).getAdActYn());
        assertEquals(1,dadDetList.get(0).getDadActYn());
    }

    @ParameterizedTest
    @MethodSource("temp")
    void isOdd_ShouldReturnTrueForOddNumbers(Ad ad) {
//        System.out.println(ad.get);
    }

    static Stream<Arguments> temmp() throws Throwable {
        return Stream.of(
                Arguments.of(Adv.builder()
                        .name("adv")
                        .password("1")
                        .role(AccountRoleEnum.valueOf("ROLE_ADV"))
                        .adIngActYn(1)
                        .balance(1000L)
                        .eventMoneyBalance(0L)
                        .dayLimitBudget(0L)
                        .build())
        );
    }


    @Test
    @DisplayName("updateAdActYnAll")
    @Transactional
//    @Transactional
    void updateAdActYnAll(){
        //given - dto
        List<Long> longList = new ArrayList<>();
        longList.add(3L);
        Integer integer = 0;

        RequestAdActYnAllDto dto = new RequestAdActYnAllDto();
        dto.setLongList(longList);
        dto.setYn(integer);

        Ad ad = createAd();
        adRepository.save(ad);

        DadDet dadDet = createDadDet(ad);
        daddetRepository.save(dadDet);

        //when
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

    @Test
    @DisplayName("findCurrentStateAdLists")
    @Transactional
    void findCurrentStateAdLists(){
        //given
        Ad ad = createAd();
        adRepository.save(ad);

        DadDet dadDet = createDadDet(ad);
        daddetRepository.save(dadDet);

        CnrReq cnrReq = CnrReq.builder()
                .dadDetId(dadDet) //직접광고 상세 ID
                .cnrIngStatus("APPROVAL") //검수 진행 상태
                .cnrInputDiv("INPUT_CNR") //검수 입력 구분
                .cnrCompleteYn(1) //검수 완료 여부(N)
                .cnrFailCause(null) //검수 실패 사유
                .cnrFailComt(null) //검수 실패 코멘트
                .build();
        cnrReqRepository.save(cnrReq);

        dadDet.setCnrReq(cnrReq);

        //when
        List<ResponseCurrentStateAdListDto> list = adService.findCurrentStateAdLists();

        //then
        assertEquals(1,list.size()); //크기 확인
        assertEquals("상품명",list.get(0).getItemName());
//        assertEquals(dto);

    }


    private Adv createAdv(){
        return Adv.builder()
                .name("adv")
                .password("1")
                .role(AccountRoleEnum.valueOf("ROLE_ADV"))
                .adIngActYn(1)
                .balance(1000L)
                .eventMoneyBalance(0L)
                .dayLimitBudget(0L)
                .build();
    }
    private Agroup createAgroup(){
        return Agroup.builder()
                .agroupName("설빈그룹")
                .agroupActYn(1)
                .agroupUseActYn(1)
                .build();
    }
    private Item createItem(){
        return Item.builder()
                .itemNo("상품번호01")
                .itemName("상품명")
                .adultYn(1)
                .itemOrgCost(1000L)
                .itemActYn(1)
                .build();
    }

    @Transactional
    Ad createAd(){
        Adv adv = createAdv();
        adv.encodePassword(passwordEncoder);
        advRepository.saveAndFlush(adv); //TODO 여기에 플러시를 왜 붙여야만 하는가?

        Agroup agroup = createAgroup();
        agroupRepository.save(agroup);

        Item item = createItem();
        itemRepository.save(item);

        return Ad.builder()
                .adv(adv)
                .agroup(agroup)
                .item(item)
                .adActYn(1)
                .adUseConfigYn(1)
                .build();
    }
    private Kwd createKwd(){
        return Kwd.builder()
                .kwdName("설빈키워드")
                .sellPossKwdYn(1)
                .manualCnrKwdYn(1)
                .build();
    }
    private DadDet createDadDet(Ad ad){
        Kwd kwd = createKwd();
        keywordRepository.save(kwd);

        return DadDet.builder()
                .ad(ad)
                .kwd(kwd)
                .dadCnr("APPROVAL")
                .cnrReqId(null)
                .dadActYn(1)
                .dadUseConfigYn(1)
                .build();
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
        public Long getKeys() {
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
