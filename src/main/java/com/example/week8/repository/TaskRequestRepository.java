package com.example.week8.repository;

import com.example.week8.entity.TaskRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRequestRepository extends JpaRepository<TaskRequest,Long> {
}
