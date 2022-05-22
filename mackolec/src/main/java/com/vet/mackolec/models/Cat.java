package com.vet.mackolec.models;


import com.vet.mackolec.models.enums.Breed;
import com.vet.mackolec.models.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cat")
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private Long id;

    @Column(name = "jmbm", unique=true, nullable=false)
    private String jmbm;

    @Column(name = "name", nullable=false)
    private String name;

    @Column(name = "age", nullable=false)
    private int age;

    @Column(name = "weight", nullable=false)
    private int weight;

    @Enumerated(EnumType.STRING)
    @Column(name = "breed", nullable=false)
    private Breed breed;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable=false)
    private Gender gender;

    @OneToMany(mappedBy = "cat")
    Set<Therapy> therapies;
}
