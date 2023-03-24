package com.example.week8.repository;

import com.example.week8.dto.kwd.find.ResponseKeywordListJoinDaddet;
import com.example.week8.entity.Kwd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeywordRepository extends JpaRepository<Kwd,Long> {

    Kwd findByKwdName(String kwdName);

    @Query(value = "select k.kwd_id as 'key', k.kwd_name as kwdName, k.manual_cnr_kwd_yn as manualCnrKwdYn, k.sell_poss_kwd_yn as sellPossKwdYn,d.ad_id as adId, d.dad_det_id as dadDetId, d.dad_act_yn as dadActYn, d.dad_use_config_yn as dadUseConfigYn\n" +
            "    from kwd k\n" +
            "    inner join dad_det d on d.kwd_id = k.kwd_id and d.ad_id = :adId and d.dad_act_yn = 1 \n" +
            "where k.kwd_name like %:kwdName%",nativeQuery = true)
    List<ResponseKeywordListJoinDaddet> keywordListJoinDadDetFind(@Param("adId") Integer adId, @Param("kwdName") String kwdName);

}
