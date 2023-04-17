package com.example.week8.repository;

import com.example.week8.entity.TaskRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRequestRepository extends JpaRepository<TaskRequest,Long> {

    List<TaskRequest> findByTaskStatus(String status);

    List<TaskRequest> findAll();

    TaskRequest findByTaskName(String name);

    TaskRequest findByTaskPath(String path);

}
