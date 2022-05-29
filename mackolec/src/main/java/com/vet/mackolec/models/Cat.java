package com.vet.mackolec.models;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.vet.mackolec.models.enums.Breed;
import com.vet.mackolec.models.enums.CatAge;
import com.vet.mackolec.models.enums.Gender;

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
@Table(name = "cat")
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private Long id;

    @NonNull
    @Column(name = "jmbm", unique=true, nullable=false)
    private String jmbm;

    @NonNull
    @Column(name = "name", nullable=false)
    private String name;

    @NonNull
    @Column(name = "age", nullable=false)
    private Integer age;
    
    @NonNull
    @Column(name = "weight", nullable=false)
    private Integer weight;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "breed", nullable=false)
    private Breed breed;
    
    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "age_enum", nullable=false)
    private CatAge ageEnum;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable=false)
    private Gender gender;

    @OneToMany(mappedBy = "cat")
    Set<Therapy> therapies;
}
