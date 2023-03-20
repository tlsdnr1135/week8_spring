package com.example.week8.repository;

import com.example.week8.dto.ad.find.AdManageDto;
import com.example.week8.dto.ad.find.AdManageResDto;
import com.example.week8.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {

    //그룹명 조회(광고 관리)
    @Query(value = "select g.agroup_id as 'key', g.agroup_name as agroupName, sum(a.ad_act_yn) as adActYn ,sum(a.ad_use_config_yn) as adUseConfigYn, g.agroup_use_act_yn as agroupUseActYn \n" +
            "    from ad a\n" +
            "    inner join agroup g on a.agroup_id = g.agroup_id \n" +
            "    inner join member m on m.member_id = :names \n" +
            "    where a.ad_act_yn = 1\n"+
            "    and g.agroup_name like %:strs% group by g.agroup_name",nativeQuery = true)
    public List<AdManageDto> findBySulbin(@Param(value = "names") String name, @Param(value="strs") String agroupName);

}
