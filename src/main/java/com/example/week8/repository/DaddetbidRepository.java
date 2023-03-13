package com.example.week8.repository;

import com.example.week8.entity.DadDetBid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaddetbidRepository extends JpaRepository<DadDetBid,Long> {
}
