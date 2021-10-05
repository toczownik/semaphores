package com.janszataniak.semaphoresbackend.controller;

import com.janszataniak.semaphoresbackend.model.Warehouse;
import com.janszataniak.semaphoresbackend.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/  restApi/warehouses")
public class WarehouseController {
    private WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseController(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Warehouse> getWarehouses() {
        return warehouseRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Warehouse getWarehouse(@PathVariable("id") int id) {
        return warehouseRepository.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Warehouse> addWarehouse(@RequestBody Warehouse warehouse) {
        warehouseRepository.save(warehouse);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Warehouse> removeWarehouse(@PathVariable("id") int id) {
        Warehouse warehouse = warehouseRepository.getById(id);
        if (warehouse == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        warehouseRepository.delete(warehouse);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
