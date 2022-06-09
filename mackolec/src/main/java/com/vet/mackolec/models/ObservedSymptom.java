package com.vet.mackolec.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "observed_symptom")
public class ObservedSymptom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "symptomId")
    private Symptom symptom;
    
    @Column(name="level")
    private Double level;

    public ObservedSymptom(Symptom symptom, Double level) {
    	this.symptom = symptom;
    	this.level = level;
    }
}

