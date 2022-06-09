package com.vet.mackolec.models.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ObservedCatDTO {

	private List<ObservedSymptomDTO> observedSymptoms;
	private CatInfoDTO catInfo;
	
}
