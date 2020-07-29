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

    @GetMapping(value = "/whiskies/{id}")
    public ResponseEntity getWhisky(@PathVariable Long id){
        return new ResponseEntity<>(whiskyRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/whiskies")
    public ResponseEntity getAllWhiskies(
            @RequestParam (name="year", required = false) int year){
        if (year == 0){
            return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Whisky>>(whiskyRepository.findWhiskiesByYear(year), HttpStatus.OK);
        }

    }

}
