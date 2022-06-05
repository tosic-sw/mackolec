package com.vet.mackolec.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vet.mackolec.events.OxygenLevelEvent;

@RestController
@RequestMapping("api/oxygenLevel")
public class OxygenLevelController {
	
	@PostMapping(consumes = "application/json")
    public ResponseEntity<String> saveFood(@RequestBody OxygenLevelEvent oxygenLevel) {
        try {
        	// Dodaj kod koji ce da zove pravila
            return new ResponseEntity<>("Oxygen level info successfully read", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>("Unknown error happend while reading oxygen level", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
