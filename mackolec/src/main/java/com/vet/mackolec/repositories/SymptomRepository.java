package com.vet.mackolec.repositories;

import com.vet.mackolec.models.Symptom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymptomRepository extends JpaRepository<Symptom, Long>{

	Symptom findByName(String name);

}
