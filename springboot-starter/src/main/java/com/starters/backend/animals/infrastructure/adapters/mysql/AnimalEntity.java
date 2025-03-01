package com.starters.backend.animals.infrastructure.adapters.mysql;

@Entity
@Table(name = "animals")
public class AnimalEntity {
    @Id
    private String id;
    private String name;
}

