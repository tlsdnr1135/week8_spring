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



}
