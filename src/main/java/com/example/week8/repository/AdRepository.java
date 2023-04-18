package com.example.week8.repository;

import com.example.week8.dto.ad.find.AdManageDto;
import com.example.week8.dto.ad.find.AdManageResDto;
import com.example.week8.dto.ad.update.ResponseCurrentStateAdListDto;
import com.example.week8.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {

    //adUseConfigYn일괄 변경
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update ad a set a.ad_use_config_yn = :yn where a.ad_id in :list",nativeQuery = true)
    void updateAdUseConfigYnAll(@Param("list")List<Long> longList, @Param("yn")Integer yn);

    //adActYn일괄 변경
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update ad a set a.ad_act_yn = :yn,a.ad_use_config_yn = :yn where a.ad_id in :list",nativeQuery = true)
    void updateAdActYnALl(@Param("list")List<Long> longList, @Param("yn")Integer yn);


    //광고 현황 테이블

    @Query(value = "select d.dad_det_id as keys, i.item_name as itemName, k.kwd_name as kwdName, i.adult_yn as adultYn from ad a \n" +
            "    inner join dad_det d on a.ad_id = d.ad_id and d.dad_act_yn=1 and d.dad_use_config_yn=1 \n" +
            "    inner join cnr_req c on c.cnr_req_id = d.cnr_req_id and c.cnr_ing_status = 'APPROVAL' \n" +
            "    inner join agroup g on a.agroup_id = g.agroup_id and g.agroup_use_act_yn = 1 and g.agroup_act_yn = 1 \n" +
            "    inner join kwd k on d.kwd_id = k.kwd_id \n" +
            "    inner join item i on a.item_id = i.item_id \n" +
            "    where a.ad_act_yn=1 and a.ad_use_config_yn=1" ,nativeQuery = true)
    List<ResponseCurrentStateAdListDto> findCurrentStateAdLists();
}
