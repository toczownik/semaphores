package com.janszataniak.semaphoresbackend.repository;

import com.janszataniak.semaphoresbackend.model.Forklift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForkliftRepository extends JpaRepository<Forklift, Integer> {
    Forklift findById(Long id);
    Boolean existsById(Long id);
}
