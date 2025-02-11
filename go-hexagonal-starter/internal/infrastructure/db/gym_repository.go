package db

import (
    "fmt" // Asegúrate de importar fmt para usar fmt.Errorf
    // Eliminar esta importación si no estás usando domain
    //"go-hexagonal-starter/internal/domain"  
)

// Implementación en memoria de GymRepository
type GymRepositoryInMemory struct {
    gyms map[string][]string
}

// Nuevo repositorio en memoria
func NewGymRepositoryInMemory() *GymRepositoryInMemory {
    return &GymRepositoryInMemory{gyms: make(map[string][]string)}
}

// Obtener horarios de gimnasio
func (r *GymRepositoryInMemory) GetGymSchedules(gymID string) ([]string, error) {
    schedules, exists := r.gyms[gymID]
    if !exists {
        return nil, fmt.Errorf("Gimnasio con ID %s no encontrado", gymID) // Asegúrate de que fmt esté importado
    }
    return schedules, nil
}

// Agregar horario al gimnasio
func (r *GymRepositoryInMemory) AddGymSchedule(gymID string, schedule string) error {
    r.gyms[gymID] = append(r.gyms[gymID], schedule)
    return nil
}
