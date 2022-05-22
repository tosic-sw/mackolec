package com.vet.mackolec.models;

import com.vet.mackolec.models.enums.TherapyStrength;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "therapy")
public class Therapy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="therapy_strength", nullable=false)
    private TherapyStrength therapyStrength;

    @Column(name = "date", nullable=false)
    private Long date;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Cat cat;

    @OneToMany(mappedBy = "therapy")
    private Set<ObservedSymptom> observedSymptoms;

    @ManyToOne
    @JoinColumn(name = "diseaseId")
    private Disease disease;

    @ManyToOne
    @JoinColumn(name = "medicineId")
    private Medicine medicine;
}
