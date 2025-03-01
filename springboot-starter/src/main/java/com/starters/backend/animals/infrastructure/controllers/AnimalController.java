package com.starters.backend.animals.infrastructure.controllers;

import com.starters.backend.animals.application.dtos.AnimalDTO;
import com.starters.backend.animals.application.usecases.GetAnimalUseCase;
import com.starters.backend.animals.domain.entities.Animal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/animals")
public class AnimalController {
    private final GetAnimalUseCase getAnimalUseCase;

    public AnimalController(GetAnimalUseCase getAnimalUseCase) {
        this.getAnimalUseCase = getAnimalUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalDTO> getAnimal(@PathVariable String id) {
        Animal animal = getAnimalUseCase.execute(id);
        return ResponseEntity.ok(new AnimalDTO(animal));
    }
}
