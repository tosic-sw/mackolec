package com.vet.mackolec.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.vet.mackolec.models.dto.SymptomDTO;
import com.vet.mackolec.services.SymptomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/symptoms")
public class SymptomController {

	@Autowired
	private SymptomService symptomService;
	
	@GetMapping
	public ResponseEntity<List<SymptomDTO>> getSymptoms(){
		return new ResponseEntity<>(symptomService.getSymptoms().stream().map(SymptomDTO::new).collect(Collectors.toList()), HttpStatus.OK);
	}
}
