package com.vet.mackolec.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
public class TestController {

	
	@GetMapping
    public ResponseEntity<String> gretting() {
        try {
            return new ResponseEntity<>("Gretting from backend!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
