package com.vet.mackolec.services;

import java.util.List;

import com.vet.mackolec.models.Symptom;

public interface SymptomService {

	List<Symptom> getSymptoms();

	Symptom findOne(String name);
}
