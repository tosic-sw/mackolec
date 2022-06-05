package com.vet.mackolec.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vet.mackolec.events.HeartBeatEvent;

@RestController
@RequestMapping("api/heartBeat")
public class HeartBeatController {

	@PostMapping(consumes = "application/json")
    public ResponseEntity<String> saveFood(@RequestBody HeartBeatEvent heartBeat) {
        try {
        	// Dodaj kod koji ce da zove pravila
            return new ResponseEntity<>("Heart beat signal successfully recieved", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>("Unknown error happend while reading heart beat", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
}
