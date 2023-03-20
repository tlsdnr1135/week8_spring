package com.example.week8.repository;

import com.example.week8.entity.Agroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AgroupRepository extends JpaRepository<Agroup,Long> {

    Agroup findByAgroupName(String str);



    //On/Off 일괄 변경
    @Transactional
    @Modifying
    @Query(value = "update agroup g set g.agroup_use_act_yn = :yn where g.agroup_id in :list",nativeQuery = true)
    void updateOnOff(@Param("list") List<Long> longList,@Param("yn") Integer num);



}
