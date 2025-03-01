package com.starters.backend.animals.application.dtos;

import com.starters.backend.animals.domain.entities.Animal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnimalDTO {

    Long id;
    String name;

    public AnimalDTO(Animal animal){
        this.id = animal.getId();
        this.name = animal.getName();
    }
}
