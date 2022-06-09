package com.vet.mackolec.repositories;

import com.vet.mackolec.models.Cat;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat, Long>{

	Cat findByJmbm(String jmbm);

}
