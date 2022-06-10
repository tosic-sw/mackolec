package com.vet.mackolec.services;

import java.util.List;

import com.vet.mackolec.models.Cat;

public interface CatService {

	Cat save(Cat cat);
	
	List<Cat> findAll();
}
