package com.janszataniak.semaphoresbackend.controller;

import com.janszataniak.semaphoresbackend.model.Semaphore;
import com.janszataniak.semaphoresbackend.model.Warehouse;
import com.janszataniak.semaphoresbackend.repository.ForkliftRepository;
import com.janszataniak.semaphoresbackend.model.Forklift;
import com.janszataniak.semaphoresbackend.repository.SemaphoreRepository;
import com.janszataniak.semaphoresbackend.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/restApi/forklifts")
public class ForkliftController {

    private ForkliftRepository forkliftRepository;
    private WarehouseRepository warehouseRepository;
    private SemaphoreRepository semaphoreRepository;

    @Autowired
    public ForkliftController(ForkliftRepository forkliftRepository, WarehouseRepository warehouseRepository,
                              SemaphoreRepository semaphoreRepository) {
        this.forkliftRepository = forkliftRepository;
        this.warehouseRepository = warehouseRepository;
        this.semaphoreRepository = semaphoreRepository;
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
        forklift.setWarehouse(warehouseRepository.getById(forklift.getWarehouse().getId()));
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

    @RequestMapping(value = "/{serialNumber}", method = RequestMethod.PATCH)
    public ResponseEntity<Forklift> updateForklift(@PathVariable("serialNumber") int serialNumber,
                                                   @RequestBody Map<String, Object> updates) {
        Forklift forklift = forkliftRepository.findBySerialNumber(serialNumber);
        if (forklift == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (updateSuccessful(forklift, updates)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private boolean updateSuccessful(Forklift forklift, Map<String, Object> updates) {
        int tempX = (Integer) updates.get("x");
        int tempY = (Integer) updates.get("y");
        if (updates.containsKey("warehouse")) {
            Warehouse warehouse = warehouseRepository.getById(((Warehouse) updates.get("warehouse")).getId());
            forklift.setWarehouse(warehouse);
            forklift.clearSemaphores();
        }
        if (moveApproved(forklift, tempX, tempY)) {
            forklift.setX(tempX);
            forklift.setY(tempY);
        } else return false;
        forkliftRepository.save(forklift);
        return true;
    }

    //nazwa funkcji bez sensu
    private boolean moveApproved(Forklift forklift, int x, int y) {
        List<Semaphore> semaphoreList = semaphoreRepository.getSemaphoresByWarehouse(forklift.getWarehouse());
        List<Semaphore> semaphoresToModify = new LinkedList<>();
        for (Semaphore semaphore: semaphoreList) {
            switch (semaphore.moveAllowed(x, y, forklift.getSerialNumber())) {
                case 0:
                    return false;
                case 1:
                    semaphoresToModify.add(semaphore);
                    break;
                default:
                    break;
            }
        }
        for (Semaphore semaphore: semaphoresToModify) {
            semaphore.setForklift(forklift);
            semaphoreRepository.save(semaphore);
        }
        return true;
    }

    @RequestMapping(value = "/warehouse/{warehouseId}", method = RequestMethod.GET)
    public List<Forklift> getForkliftsByWarehouse(@PathVariable("warehouseId") int warehouseId) {
        return forkliftRepository.findByWarehouseId(warehouseId);
    }
}
