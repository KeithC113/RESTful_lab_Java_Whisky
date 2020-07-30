package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @Autowired
    DistilleryRepository distilleryRepository;

    @GetMapping(value = "/whiskies/{id}")
    public ResponseEntity getWhisky(@PathVariable Long id) {
        return new ResponseEntity<>(whiskyRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/whiskies")
    public ResponseEntity getAllWhiskies(
            @RequestParam(name = "year", required = false) Integer year,
            @RequestParam(name = "distillery", required = false) String distillery,
            @RequestParam(name = "age", required = false) Integer age,
            @RequestParam(name = "region", required = false) String region)
    {
        if (year != null) {
            return new ResponseEntity<>(whiskyRepository.findWhiskiesByYear(year), HttpStatus.OK);
        }
            if (distillery != null && age != null) {
                return new ResponseEntity<>(whiskyRepository.findByDistilleryNameAndAge(distillery, age), HttpStatus.OK);
            }
            if (region !=null){
                return new ResponseEntity(whiskyRepository.findByDistilleryRegion(region), HttpStatus.OK);
            }
        return new ResponseEntity<List<Whisky>>(whiskyRepository.findAll(), HttpStatus.OK);
        }
}
