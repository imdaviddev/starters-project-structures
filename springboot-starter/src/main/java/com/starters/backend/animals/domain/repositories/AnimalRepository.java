package com.starters.backend.animals.domain.repositories;

import com.starters.backend.animals.domain.entities.Animal;

import java.util.Optional;

public interface AnimalRepository {
    Optional<Animal> findById(String id);
    void save(Animal animal);
}
