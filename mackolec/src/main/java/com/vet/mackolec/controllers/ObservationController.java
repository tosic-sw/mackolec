package com.vet.mackolec.controllers;

import com.vet.mackolec.models.Cat;
import com.vet.mackolec.models.Therapy;
import com.vet.mackolec.models.dto.ObservedCatDTO;
import com.vet.mackolec.models.dto.TherapyDTO;
import com.vet.mackolec.services.ObservationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/observation")
public class ObservationController {
	
	@Autowired
	private ObservationService observationService;
	
	@PostMapping
	public ResponseEntity<TherapyDTO> saveObservation(@RequestBody ObservedCatDTO observedCatDTO) {
		Cat cat = new Cat(observedCatDTO.getCatInfo());
		Therapy therapy = observationService.observeCat(cat, observedCatDTO.getObservedSymptoms());
		return new ResponseEntity<>(new TherapyDTO(therapy), HttpStatus.OK);
	}
}
