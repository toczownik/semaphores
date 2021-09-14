package com.janszataniak.semaphoresbackend.controller;

import com.janszataniak.semaphoresbackend.repository.ForkliftRepository;
import com.janszataniak.semaphoresbackend.model.Forklift;
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

    @Autowired
    public ForkliftController(ForkliftRepository forkliftRepository) {
        this.forkliftRepository = forkliftRepository;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Forklift getForkliftById(@PathVariable("id") Long id) {
        return forkliftRepository.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Forklift> getForklifts() {
        return forkliftRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Forklift> deleteForkliftById(@PathVariable("id") Long id) {
        Forklift forklift = forkliftRepository.findById(id);
        forkliftRepository.delete(forklift);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Forklift> addForklift(@RequestBody Forklift forklift) {
        forkliftRepository.save(forklift);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Forklift> putForklift(@PathVariable("id") Long id, @RequestBody Forklift forklift) {
        forklift.setId(id);
        forkliftRepository.save(forklift);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void update(Forklift forklift, int x, int y) {
        forklift.setX(x);
        forklift.setY(y);

    }
}
