package com.vet.mackolec.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vet.mackolec.events.OxygenLevelEvent;
import com.vet.mackolec.exceptions.HospitalizedCatException;
import com.vet.mackolec.services.OxygenLevelService;

@RestController
@RequestMapping("api/oxygenLevel")
public class OxygenLevelController {
	
	@Autowired
	private OxygenLevelService oxygenLevelService;
	
	@PostMapping(consumes = "application/json")
    public ResponseEntity<String> readOxygenLevel(@RequestBody OxygenLevelEvent oxygenLevel) {
		try {
			oxygenLevelService.resonate(oxygenLevel);
            return new ResponseEntity<>("Oxygen level info successfully read", HttpStatus.CREATED);
        } catch (HospitalizedCatException e) {
        	return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
        	e.printStackTrace();
            return new ResponseEntity<String>("Unknown error happend while reading oxygen level", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
