package com.vet.mackolec.models;

import com.vet.mackolec.models.enums.Breed;
import com.vet.mackolec.models.enums.CatAge;
import com.vet.mackolec.models.enums.MedicineCategory;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "medicine")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private Long id;

    @NonNull
    @Column(name="name", unique=true, nullable=false)
    private String name;

    @NonNull
    @Column(name="female_fit", nullable=false)
    private Boolean femaleFit;

    @NonNull
    @Column(name="male_fit", nullable=false)
    private Boolean maleFit;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name="category", nullable=false)
    private MedicineCategory category;

    @NonNull
    @OneToMany(mappedBy = "medicine")
    private Set<Therapy> therapies;
    
    @NonNull
    @ManyToOne
    @JoinColumn(name = "disease_id")
    private Disease disease;

    @NonNull
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "medicine_unsuitable_age", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    Set<CatAge> unsuitableForAge;

    @NonNull
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "medicine_unsuitable_breed", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    Set<Breed> unsuitableForBreed;
}
