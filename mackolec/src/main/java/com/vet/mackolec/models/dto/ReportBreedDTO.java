package com.vet.mackolec.models.dto;

import java.util.List;

import com.vet.mackolec.models.helper.ReportBreed;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportBreedDTO {

	private List<ReportBreed> breeds;
}
