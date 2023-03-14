package com.example.week8.repository;

import com.example.week8.entity.Kwd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends JpaRepository<Kwd,Long> {

    Kwd findByKwdName(String kwdName);

}
