package com.vet.mackolec.services;

import com.vet.mackolec.models.HospitalizedCat;

public interface HospitalizedCatService {

	HospitalizedCat findOneByJmbm(String jmbm);
}
