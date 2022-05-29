package com.vet.mackolec.models.helper;

import java.util.List;

import com.vet.mackolec.models.Cat;
import com.vet.mackolec.models.ObservedSymptom;
import com.vet.mackolec.models.enums.Hospitalization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CatSymptomsObservation {

	private Cat cat;
	
	private List<ObservedSymptom> observedSymptoms;
	
	private Hospitalization hospitalization;
}
