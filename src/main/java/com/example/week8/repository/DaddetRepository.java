package com.example.week8.repository;

import com.example.week8.entity.DadDet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaddetRepository extends JpaRepository<DadDet,Long> {



}
