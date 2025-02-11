package http

import (
	"go-hexagonal-starter/internal/app"
	"go-hexagonal-starter/internal/domain"
	"github.com/gofiber/fiber/v2"
)

type GymHandler struct {
	service *app.GymService
}

func NewGymHandler(service *app.GymService) *GymHandler {
	return &GymHandler{service: service}
}

func (h *GymHandler) GetGymSchedules(c *fiber.Ctx) error {
	id := c.Params("id")
	gym, err := h.service.GetGymSchedules(id)
	if err != nil {
		return c.Status(404).SendString("Gimnasio no encontrado")
	}
	return c.JSON(gym)
}

func (h *GymHandler) AddGymSchedule(c *fiber.Ctx) error {
	id := c.Params("id")
	var horarios []string
	if err := c.BodyParser(&horarios); err != nil {
		return c.Status(400).SendString("Error al parsear los horarios")
	}

	gym, err := h.service.AddGymSchedule(id, horarios)
	if err != nil {
		return c.Status(404).SendString("Gimnasio no encontrado")
	}

	return c.JSON(gym)
}
