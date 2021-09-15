package com.janszataniak.semaphoresbackend.controller;

import com.janszataniak.semaphoresbackend.model.Warehouse;
import com.janszataniak.semaphoresbackend.repository.ForkliftRepository;
import com.janszataniak.semaphoresbackend.model.Forklift;
import com.janszataniak.semaphoresbackend.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("restApi/forklifts")
public class ForkliftController {

    private ForkliftRepository forkliftRepository;
    private WarehouseRepository warehouseRepository;

    @Autowired
    public ForkliftController(ForkliftRepository forkliftRepository, WarehouseRepository warehouseRepository) {
        this.forkliftRepository = forkliftRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @RequestMapping(value = "/{serialNumber}", method = RequestMethod.GET)
    public Forklift getForkliftBySerialNumber(@PathVariable("serialNumber") int serialNumber) {
        return forkliftRepository.findBySerialNumber(serialNumber);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Forklift> getForklifts() {
        return forkliftRepository.findAll();
    }

    @RequestMapping(value = "/{serialNumber}", method = RequestMethod.DELETE)
    public ResponseEntity<Forklift> deleteForkliftBySerialNumber(@PathVariable("serialNumber") int serialNumber) {
        Forklift forklift = forkliftRepository.findBySerialNumber(serialNumber);
        if (forklift == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        forkliftRepository.delete(forklift);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Forklift> addForklift(@RequestBody Forklift forklift) {
        forkliftRepository.save(forklift);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{serialNumber}", method = RequestMethod.PUT)
    public ResponseEntity<Forklift> putForklift(@PathVariable("serialNumber") int serialNumber,
                                                @RequestBody Forklift forklift) {
        forklift.setSerialNumber(serialNumber);
        forkliftRepository.save(forklift);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{warehouseId}", method = RequestMethod.GET)
    public List<Forklift> getForkliftsByWarehouse(@PathVariable("warehouseId") int warehouseId) {
        return forkliftRepository.findByWarehouseId(warehouseId);
    }

    private void update(Forklift forklift, int x, int y) {
        forklift.setX(x);
        forklift.setY(y);

    }
}
