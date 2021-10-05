package com.janszataniak.semaphoresbackend.repository;

import com.janszataniak.semaphoresbackend.model.Forklift;
import com.janszataniak.semaphoresbackend.model.Semaphore;
import com.janszataniak.semaphoresbackend.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForkliftRepository extends JpaRepository<Forklift, Integer> {
    Forklift findBySerialNumber(int serialNumber);

    List<Forklift> findByWarehouse(Warehouse warehouse);

    List<Forklift> findByWarehouseId(int warehouseId);
}
