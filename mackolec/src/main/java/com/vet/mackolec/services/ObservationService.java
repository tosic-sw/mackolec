package com.vet.mackolec.services;

import java.util.List;

import com.vet.mackolec.models.Cat;
import com.vet.mackolec.models.Therapy;
import com.vet.mackolec.models.dto.ObservedSymptomDTO;

public interface ObservationService {

	Therapy observeCat(Cat cat, List<ObservedSymptomDTO> observedSymptoms);
}
