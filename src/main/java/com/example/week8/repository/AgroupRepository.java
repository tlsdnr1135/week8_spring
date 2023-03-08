package com.example.week8.repository;

import com.example.week8.entity.Agroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgroupRepository extends JpaRepository<Agroup,Long> {
}
