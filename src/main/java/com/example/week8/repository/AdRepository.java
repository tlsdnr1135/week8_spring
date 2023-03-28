package com.example.week8.repository;

import com.example.week8.dto.ad.find.AdManageDto;
import com.example.week8.dto.ad.find.AdManageResDto;
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
    @Modifying
    @Query(value = "update ad a set a.ad_use_config_yn = :yn where a.ad_id in :list",nativeQuery = true)
    void updateAdUseConfigYnAll(@Param("list")List<Long> longList, @Param("yn")Integer yn);

    //adActYn일괄 변경
    @Transactional
    @Modifying
    @Query(value = "update ad a set a.ad_act_yn = :yn,a.ad_use_config_yn = :yn where a.ad_id in :list",nativeQuery = true)
    void updateAdActYnALl(@Param("list")List<Long> longList, @Param("yn")Integer yn);

}
