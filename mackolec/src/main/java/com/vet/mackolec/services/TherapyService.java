package com.vet.mackolec.services;

import com.vet.mackolec.models.Therapy;

public interface TherapyService {

	Therapy getLastTherapyOfCat(String jmbm);

	void save(Therapy therapy);
}
