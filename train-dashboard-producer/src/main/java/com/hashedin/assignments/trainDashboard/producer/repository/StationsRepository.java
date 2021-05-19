package com.hashedin.assignments.trainDashboard.producer.repository;

import com.hashedin.assignments.trainDashboard.producer.entity.StationsData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationsRepository extends JpaRepository<StationsData,Integer> {
}
