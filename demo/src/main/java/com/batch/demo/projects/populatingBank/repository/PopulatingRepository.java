package com.batch.demo.projects.populatingBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.batch.demo.projects.populatingBank.enitity.PopulatingEntity;

public interface PopulatingRepository extends JpaRepository<PopulatingEntity, Long>{
    
}
