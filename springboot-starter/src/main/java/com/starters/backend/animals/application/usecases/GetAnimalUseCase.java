package com.starters.backend.animals.application.usecases;

import com.starters.backend.animals.domain.entities.Animal;
import com.starters.backend.animals.domain.exceptions.NotFoundException;
import com.starters.backend.animals.domain.repositories.AnimalRepository;

public class GetAnimalUseCase {
    private final AnimalRepository repository;

    public GetAnimalUseCase(AnimalRepository repository) {
        this.repository = repository;
    }

    public Animal execute(String id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Animal not found"));
    }
}
