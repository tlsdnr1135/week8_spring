package com.example.week8.repository;

import com.example.week8.entity.Agroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgroupRepository extends JpaRepository<Agroup,Long> {

    Agroup findByAgroupName(String str);

    @Query("select g from AGROUP g join fetch g.ad")
    List<Agroup> findByAgroupNameLike(String str);

}
