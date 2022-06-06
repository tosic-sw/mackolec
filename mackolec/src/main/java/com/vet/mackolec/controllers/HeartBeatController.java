package com.vet.mackolec.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vet.mackolec.events.HeartBeatEvent;
import com.vet.mackolec.exceptions.HospitalizedCatException;
import com.vet.mackolec.services.HeartBeatService;

@RestController
@RequestMapping("api/heartBeat")
public class HeartBeatController {

	@Autowired
	private HeartBeatService heartBeatService;
	
	@PostMapping(consumes = "application/json")
    public ResponseEntity<String> readHeartBeat(@RequestBody HeartBeatEvent heartBeat) {
        try {
        	heartBeatService.resonate(heartBeat);
            return new ResponseEntity<>("Heart beat signal successfully recieved", HttpStatus.CREATED);
        } catch (HospitalizedCatException e) {
        	return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
        	e.printStackTrace();
            return new ResponseEntity<String>("Unknown error happend while reading heart beat", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
}
