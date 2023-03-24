package com.example.week8.repository;

import com.example.week8.entity.DadDet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DaddetRepository extends JpaRepository<DadDet,Long> {

    //dadUseConfigYn일괄 변경 - adId기준
    @Transactional
    @Modifying
    @Query(value = "update dad_det d set d.dad_use_config_yn = :yn where d.ad_id in :list",nativeQuery = true)
    void updateDadUseConfigYnAll(@Param("list") List<Long> longList, @Param("yn")Integer yn);

    //dadUseConfigYn일괄 변경 - id기준
    @Transactional
    @Modifying
    @Query(value = "update dad_det d set d.dad_use_config_yn = :yn where d.dad_det_id in :list",nativeQuery = true)
    void updateDadUseConfigYnAll2(@Param("list") List<Long> longList, @Param("yn")Integer yn);

    //dadActYn일괄 변경 - adId기준
    @Transactional
    @Modifying
    @Query(value = "update dad_det d set d.dad_act_yn = :yn where d.ad_id in :list",nativeQuery = true)
    void updateDadActYnAll(@Param("list") List<Long> longList, @Param("yn")Integer yn);

    //dadActYn일괄 변경 - id기준
    @Transactional
    @Modifying
    @Query(value = "update dad_det d set d.dad_act_yn = :yn where d.dad_det_id in :list",nativeQuery = true)
    void updateDadActYnAll2(@Param("list") List<Long> longList, @Param("yn")Integer yn);

}
