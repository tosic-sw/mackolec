package com.vet.mackolec.models.dto;

import com.vet.mackolec.models.Cat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CatInfoDTO {
	
	private String jmbm;
    private String name;
    private Integer age;
    private Integer weight;
    private String breed;
    private String gender;
    
    public CatInfoDTO(Cat cat) {
    	this.jmbm = cat.getJmbm();
    	this.name = cat.getName();
    	this.age = cat.getAge();
    	this.weight = cat.getWeight();
    	this.breed = cat.getBreed().toString();
    	this.gender = cat.getGender().toString();
    }
}
