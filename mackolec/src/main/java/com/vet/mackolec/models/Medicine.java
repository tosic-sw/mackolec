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
@AllArgsConstructor
@Entity
@Table(name = "medicine")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private Long id;

    @Column(name="name", unique=true, nullable=false)
    private String name;

    @Column(name="female_fit", nullable=false)
    private boolean femaleFit;

    @Column(name="male_fit", nullable=false)
    private boolean maleFit;

    @Enumerated(EnumType.STRING)
    @Column(name="category", nullable=false)
    private MedicineCategory category;

    @OneToMany(mappedBy = "medicine")
    private Set<Therapy> therapies;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "medicine_unsuitable_age", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    Set<CatAge> unsuitableForAge;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "medicine_unsuitable_breed", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    Set<Breed> unsuitableForBreed;
}
