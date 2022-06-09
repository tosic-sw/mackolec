package com.vet.mackolec.services.impl;

import com.vet.mackolec.models.Cat;
import com.vet.mackolec.repositories.CatRepository;
import com.vet.mackolec.services.CatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatServiceImpl implements CatService {

	@Autowired
	private CatRepository catRepository;
	
	@Override
	public Cat save(Cat cat) {
		Cat catFromDB = catRepository.findByJmbm(cat.getJmbm());
		if (catFromDB == null)
			return catRepository.save(cat);
		else {
			catFromDB.setAge(cat.getAge());
			catFromDB.setWeight(cat.getWeight());
			catFromDB = catRepository.save(catFromDB);
			return catFromDB;
		}
	}

	
}
