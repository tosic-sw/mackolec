package com.vet.mackolec.models.dto;

import com.vet.mackolec.models.ObservedSymptom;
import com.vet.mackolec.models.Symptom;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SymptomDTO {

	private String name;
	
	public SymptomDTO(Symptom symptom) {
		this.name = symptom.getName();
	}
	
	public SymptomDTO(ObservedSymptom observedSymptom) {
		this.name = observedSymptom.getSymptom().getName();
	}
}
