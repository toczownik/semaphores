package com.janszataniak.semaphoresbackend.repository;

import com.janszataniak.semaphoresbackend.model.Semaphore;
import com.janszataniak.semaphoresbackend.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SemaphoreRepository extends JpaRepository<Semaphore, Integer> {
    Semaphore getSemaphoreById(int id);
    List<Semaphore> getSemaphoresByWarehouse(Warehouse warehouse);
    List<Semaphore> getSemaphoresByWarehouseId(int warehouseId);
    List<Semaphore> getSemaphoresByxBetween(int min, int max);
    List<Semaphore> getSemaphoresByyBetween(int min, int max);
}
