package com.janszataniak.semaphoresbackend.repository;

import com.janszataniak.semaphoresbackend.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    Warehouse getById(int id);
}
