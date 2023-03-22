package com.example.week8.repository;

import com.example.week8.dto.ad.find.AdManageDto;
import com.example.week8.entity.Agroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgroupRepository extends JpaRepository<Agroup,Long> {

    Optional<Agroup> findByAgroupName(String str);
    Boolean existsByAgroupName(String name);



    //On/Off 일괄 변경(agroup_use_act_yn)
    @Transactional
    @Modifying
    @Query(value = "update agroup g set g.agroup_use_act_yn = :yn where g.agroup_id in :list",nativeQuery = true)
    void updateOnOff(@Param("list") List<Long> longList,@Param("yn") Integer num);

    //그룹명 조회(광고 관리)
    @Query(value = "select g.reg_time as 'regTime',g.agroup_id as 'key', g.agroup_name as agroupName,ifnull(sum(a.ad_act_yn),0) as adActYn ,ifnull(sum(a.ad_use_config_yn),0) as adUseConfigYn, g.agroup_use_act_yn as agroupUseActYn \n" +
            "    from agroup g\n" +
            "    left join ad a on a.agroup_id = g.agroup_id and a.adv_id= :names \n" +
            "    where g.agroup_act_yn= 1\n"+
            "    and g.agroup_name like %:strs% group by g.agroup_name",nativeQuery = true)
    public List<AdManageDto> findByAgroupListJoinAd(@Param(value = "names") String name, @Param(value="strs") String agroupName);

    //그룹 삭제,(실제삭제x, 광고그룹 활성 여부 off, agroupActYn)
    @Transactional
    @Modifying
    @Query(value = "update agroup g set g.agroup_act_yn = 0 where g.agroup_id in :list",nativeQuery = true)
    void updateAgroupActYnOff(@Param("list") List<Long> longList);

}
