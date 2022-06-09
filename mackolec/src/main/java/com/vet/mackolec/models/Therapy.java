package com.vet.mackolec.models;

import com.vet.mackolec.models.enums.Hospitalization;
import com.vet.mackolec.models.enums.TherapyStrength;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

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
    
    @Column(name = "current_age")
    private Integer currentAge;
    
    @Column(name = "current_weight")
    private Integer currentWeight;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Cat cat;

    @NonNull
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ObservedSymptom> observedSymptoms;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "diseaseId")
    private Disease disease;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "medicineId")
    private Medicine medicine;
    
}
