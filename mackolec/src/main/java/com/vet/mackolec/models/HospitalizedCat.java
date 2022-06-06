package com.vet.mackolec.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "hospitalized_cat")
public class HospitalizedCat {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private Long id;
	
    @Column(name = "date", nullable=false)
    private Long date;
    
    @OneToOne
    @JoinColumn(name = "cat_id", nullable = false)
	private Cat cat;
	
	public HospitalizedCat() {}
	
	public HospitalizedCat(Long date, Cat cat) {
		this.date = date;
		this.cat = cat;
	}
}
