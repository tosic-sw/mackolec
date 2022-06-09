package com.vet.mackolec.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vet.mackolec.dto.HospitalizedCatDTO;
import com.vet.mackolec.models.Therapy;
import com.vet.mackolec.services.TherapyService;
import com.vet.mackolec.utils.ControllerUtils;

@RestController
@RequestMapping("api/therapy")
public class TherapyController {

	@Autowired
	private TherapyService therapyService;
	
	@GetMapping(consumes = "application/json")
    public ResponseEntity<List<HospitalizedCatDTO>> search(@RequestParam("search") String search, Pageable pageable) {
		try {
			Page<Therapy> therapiesPage = therapyService.search(search, pageable);
			// List<TherapyDTO> therapiesDTO= new ArrayList<TherapyDTO>();
			// therapiesPage.getContent().forEach((therapy) -> therapiesDTO.add(new TherapyDTO(therapy)));
			
            return new ResponseEntity<>(null,  ControllerUtils.createPageHeaderAttributes(therapiesPage), HttpStatus.CREATED);
        } catch (Exception e) {
        	e.printStackTrace();
        	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
}
