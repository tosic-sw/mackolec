package com.vet.mackolec.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vet.mackolec.events.FluidFlowEvent;
import com.vet.mackolec.exceptions.HospitalizedCatException;
import com.vet.mackolec.services.FluidFlowService;

@RestController
@RequestMapping("api/fluidFlow")
public class FluidFlowController {
	
	@Autowired
	private FluidFlowService fluidFlowService;
	
	@PostMapping(consumes = "application/json")
    public ResponseEntity<String> readFluidFlow(@RequestBody FluidFlowEvent fluidFlowLevel) {
		try {
			fluidFlowService.resonate(fluidFlowLevel);
            return new ResponseEntity<>("Fluid flow level read", HttpStatus.CREATED);
        } catch (HospitalizedCatException e) {
        	return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
        	e.printStackTrace();
            return new ResponseEntity<String>("Unknown error happend while reading fluid level", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
}
