package app

// Si no estás usando "domain", elimina esta línea
// import "go-hexagonal-starter/internal/domain" 

// GymRepository es la interfaz que el repositorio debe implementar.
type GymRepository interface {
    GetGymSchedules(gymID string) ([]string, error)
    AddGymSchedule(gymID string, schedule string) error
}

type GymService struct {
    gymRepo GymRepository
}

// Nuevo servicio de gimnasio
func NewGymService(gymRepo GymRepository) *GymService {
    return &GymService{gymRepo: gymRepo}
}

// Obtener horarios del gimnasio
func (s *GymService) GetGymSchedules(gymID string) ([]string, error) {
    return s.gymRepo.GetGymSchedules(gymID)
}

// Agregar horarios del gimnasio
func (s *GymService) AddGymSchedule(gymID string, schedule string) error {
    return s.gymRepo.AddGymSchedule(gymID, schedule)
}
