package com.janszataniak.semaphoresbackend.controller;

import com.janszataniak.semaphoresbackend.model.Semaphore;
import com.janszataniak.semaphoresbackend.model.Warehouse;
import com.janszataniak.semaphoresbackend.repository.SemaphoreRepository;
import com.janszataniak.semaphoresbackend.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/restApi/semaphores")
public class SemaphoreController {
    private SemaphoreRepository semaphoreRepository;
    private WarehouseRepository warehouseRepository;

    @Autowired
    public SemaphoreController(SemaphoreRepository semaphoreRepository, WarehouseRepository warehouseRepository) {
        this.semaphoreRepository = semaphoreRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Semaphore> getSemaphores() {
        return semaphoreRepository.findAll();
    }

    @RequestMapping(value = "/{warehouseId}", method = RequestMethod.GET)
    public List<Semaphore> getSemaphoresByWarehouse(@PathVariable("warehouseId") int warehouseId) {
        return semaphoreRepository.getSemaphoresByWarehouseId(warehouseId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Semaphore> addSemaphore(@RequestBody Semaphore semaphore) {
        semaphore.setWarehouse(warehouseRepository.getById(semaphore.getWarehouse().getId()));
        semaphoreRepository.save(semaphore);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Semaphore> removeSemaphore(@PathVariable("id") int id) {
        Semaphore semaphore = semaphoreRepository.getSemaphoreById(id);
        if(semaphore == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        semaphoreRepository.delete(semaphore);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
