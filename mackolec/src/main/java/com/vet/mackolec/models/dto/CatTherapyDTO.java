package com.vet.mackolec.models.dto;

import com.vet.mackolec.models.Therapy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CatTherapyDTO {

	private String therapyStrength;
    private Long date;
    private Integer catAge;
    private Integer catWeight;
    private String diseaseName;
    private String medicineName;
    private String medicineCategory;
    
    public CatTherapyDTO(Therapy therapy) {
    	this.therapyStrength = therapy.getTherapyStrength().toString();
    	this.date = therapy.getDate();
    	this.catAge = therapy.getCurrentAge();
    	this.catWeight = therapy.getCurrentWeight();
    	this.diseaseName = therapy.getDisease().getName();
    	this.medicineName = therapy.getMedicine().getName();
    	this.medicineCategory = therapy.getMedicine().getCategory().toString();
    }
}
