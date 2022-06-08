package com.vet.mackolec.controllers;

import java.util.ArrayList;
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
import com.vet.mackolec.models.HospitalizedCat;
import com.vet.mackolec.services.HospitalizedCatService;
import com.vet.mackolec.utils.ControllerUtils;

@RestController
@RequestMapping("api/hospitalizedCat")
public class HospitalizedCatController {
	
	@Autowired
	private HospitalizedCatService hospitalizedCatService;
	
	@GetMapping(consumes = "application/json")
    public ResponseEntity<List<HospitalizedCatDTO>> search(@RequestParam("search") String search, Pageable pageable) {
		try {
			Page<HospitalizedCat> hcatsPage = hospitalizedCatService.search(search, pageable);
			List<HospitalizedCatDTO> hcats = new ArrayList<HospitalizedCatDTO>();
			hcatsPage.getContent().forEach((hcat) -> hcats.add(new HospitalizedCatDTO(hcat)));
			
            return new ResponseEntity<>(hcats,  ControllerUtils.createPageHeaderAttributes(hcatsPage), HttpStatus.CREATED);
        } catch (Exception e) {
        	e.printStackTrace();
        	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
//	@GetMapping
//    public ResponseEntity<List<AlarmNotificationEvent>> search(@RequestParam("notificationType") String notificationType, Pageable pageable) {
//        try {
//        	Page<AlarmNotificationEvent> alarmNotifications = alarmNotificationService.search(notificationType, pageable);
//            return new ResponseEntity<>(alarmNotifications.getContent(), ControllerUtils.createPageHeaderAttributes(alarmNotifications), HttpStatus.OK);
//        } catch (Exception e) {
//        	e.printStackTrace();
//            return new ResponseEntity<List<AlarmNotificationEvent>>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
