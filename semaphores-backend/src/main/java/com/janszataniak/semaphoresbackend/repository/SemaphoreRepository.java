package com.janszataniak.semaphoresbackend.repository;

import com.janszataniak.semaphoresbackend.model.Semaphore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SemaphoreRepository extends JpaRepository<Semaphore, Integer> {
    Semaphore getSemaphoreById(int id);
    List<Semaphore> getSemaphoresByWarehouseId(int warehouseId);
}
