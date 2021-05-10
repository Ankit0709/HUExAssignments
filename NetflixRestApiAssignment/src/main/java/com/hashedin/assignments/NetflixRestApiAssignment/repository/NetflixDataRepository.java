package com.hashedin.assignments.NetflixRestApiAssignment.repository;

import com.hashedin.assignments.NetflixRestApiAssignment.models.NetflixDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NetflixDataRepository extends JpaRepository<NetflixDataModel,String> {

}
