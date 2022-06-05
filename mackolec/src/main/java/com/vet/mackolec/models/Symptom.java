package com.vet.mackolec.models;


import lombok.*;

import javax.persistence.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "symptom")
public class Symptom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private Long id;

    @NonNull
    @Column(name="name", unique=true, nullable=false)
    private String name;

    @OneToMany(mappedBy = "symptom")
    Set<ObservedSymptom> observedSymptoms;
    
    @ManyToMany(mappedBy = "symptoms")
    private Set<Disease> diseases; 
}
