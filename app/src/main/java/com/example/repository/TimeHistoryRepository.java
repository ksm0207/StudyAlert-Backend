package com.example.repository;

import com.example.entity.TimeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeHistoryRepository extends JpaRepository<TimeHistory, Long> {
}
