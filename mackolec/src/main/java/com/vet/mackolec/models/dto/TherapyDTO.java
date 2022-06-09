package com.vet.mackolec.models.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.vet.mackolec.models.Therapy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TherapyDTO {

    private String therapyStrength;
    private Long date;
    private String hospitalization;
    private CatInfoDTO cat;
    private Integer catAge;
    private Integer catWeight;
    private List<ObservedSymptomDTO> observedSymptoms;
    private String diseaseName;
    private String medicineName;
    
    public TherapyDTO(Therapy therapy) {
    	this.therapyStrength = therapy.getTherapyStrength().toString();
    	this.date = therapy.getDate();
    	this.hospitalization = therapy.getHospitalization().toString();
    	this.cat = new CatInfoDTO(therapy.getCat());
    	this.catAge = therapy.getCurrentAge();
    	this.catWeight = therapy.getCurrentWeight();
    	this.observedSymptoms = therapy.getObservedSymptoms()
    							.stream().map(ObservedSymptomDTO::new).collect(Collectors.toList());
    	this.diseaseName = therapy.getDisease().getName();
    	this.medicineName = therapy.getMedicine().getName();
    }
}
