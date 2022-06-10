package com.vet.mackolec.models.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.vet.mackolec.models.Cat;
import com.vet.mackolec.models.Therapy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CatTherapiesReportDTO {
	
	private CatInfoDTO cat;
	private List<CatTherapyDTO> therapies;
	
	public CatTherapiesReportDTO(Cat cat, List<Therapy> therapies) {
		this.cat = new CatInfoDTO(cat);
		this.therapies = therapies.stream().map(CatTherapyDTO::new).collect(Collectors.toList());
	}
}
