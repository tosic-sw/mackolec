package com.vet.mackolec.dto;

import com.vet.mackolec.models.HospitalizedCat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HospitalizedCatDTO extends ErrorDTO {
	private String jmbm;
	private String name;
	private Long date;
	private Integer age;
	private Integer weight;
	private String breed;
	private String gender;
	
	public HospitalizedCatDTO(HospitalizedCat hcat) {
		this.jmbm = hcat.getCat().getJmbm();
		this.name = hcat.getCat().getName();
		this.date = hcat.getDate();
		this.age = hcat.getCat().getAge();
		this.weight = hcat.getCat().getWeight();
		this.breed = hcat.getCat().getBreed().toString();
		this.gender = hcat.getCat().getGender().toString();
	}
	
}
