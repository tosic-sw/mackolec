package com.vet.mackolec.models;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "therapyId")
    private Therapy therapy;
}

