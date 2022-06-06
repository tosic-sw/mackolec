package com.vet.mackolec.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.vet.mackolec.models.enums.Hospitalization;
import com.vet.mackolec.models.enums.TherapyStrength;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "therapy")
public class Therapy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private Long id;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name="therapy_strength", nullable=false)
    private TherapyStrength therapyStrength;

    @NonNull
    @Column(name = "date", nullable=false)
    private Long date;
    
    @NonNull
    @Column(name = "hospitalization", nullable=false)
    private Hospitalization hospitalization;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Cat cat;

    @NonNull
    @OneToMany(mappedBy = "therapy")
    private Set<ObservedSymptom> observedSymptoms;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "diseaseId")
    private Disease disease;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "medicineId")
    private Medicine medicine;

	@Override
	public String toString() {
		return "Therapy [cat=" + cat + ", date= " + new SimpleDateFormat("MM-dd-yyyy").format((new Date(date))) +", medicine=" + medicine + "]";
	}
    
    
}
