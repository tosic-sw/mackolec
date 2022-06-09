package com.vet.mackolec.models.dto;

import com.vet.mackolec.models.ObservedSymptom;
import com.vet.mackolec.models.Symptom;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ObservedSymptomDTO extends SymptomDTO {
	
	private Integer intensity;
	
	public ObservedSymptomDTO(Symptom symptom) {
		super(symptom);
		this.intensity = 0;
	}
	
	public ObservedSymptomDTO(ObservedSymptom observedSymptom) {
		super(observedSymptom);
		this.intensity = observedSymptom.getLevel().intValue();
	}
}
