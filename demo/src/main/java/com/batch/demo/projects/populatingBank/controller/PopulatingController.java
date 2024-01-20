package com.batch.demo.projects.populatingBank.controller;

import org.springframework.web.bind.annotation.RestController;

import com.batch.demo.apiResponse.apiResponse;
import com.batch.demo.projects.populatingBank.service.PopulatingService;
import com.batch.demo.projects.populatingBank.service.DTO.BatchDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Locale;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;




@RestController
@RequestMapping("/populating")
public class PopulatingController {
    
    @Autowired
    private PopulatingService populatingService;

    @GetMapping
    public ResponseEntity<apiResponse> populate() {
        Instant startInstant = Instant.now();

        populatingService.populate();

        Instant endInstant = Instant.now();
        long processingTimeMillis = endInstant.toEpochMilli() - startInstant.toEpochMilli();

        System.out.println("Processing time: " + processingTimeMillis);
        System.out.println("seconds:"+ processingTimeMillis/1000 + " seconds");
        return ResponseEntity.status(HttpStatus.SC_OK)
                .body(new apiResponse("Successful Operation!"));
    }


    @PutMapping("/increaseAll")
    public ResponseEntity<apiResponse> increaseAllDeposits(){
        Instant startTime = Instant.now();

        populatingService.increaseAllDeposits();

        Instant endTime = Instant.now();
        long timeSpend= endTime.toEpochMilli() - startTime.toEpochMilli();
        System.out.println("seconds:"+ timeSpend/1000 + " seconds");

        return ResponseEntity.status(HttpStatus.SC_OK)
                .body(new apiResponse("Successful Operation!"));
    }
   
    
    @PutMapping("/increaseInBatches")
    public ResponseEntity<apiResponse> increaseInBatches(@RequestBody BatchDTO data){
        Instant startTime = Instant.now();

        populatingService.increaseInBatches(data);

        Instant endTime = Instant.now();
        long timeSpend= endTime.toEpochMilli() - startTime.toEpochMilli();
        System.out.println("time: "+ timeSpend);

        return ResponseEntity.status(HttpStatus.SC_OK)
                .body(new apiResponse("Successful Operation!"));
    } 

    @PostMapping
    public int optimumBatch(){
        return populatingService.optimumBatch();
    }
    
}
