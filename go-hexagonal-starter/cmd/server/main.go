package main

import (
    "fmt"
    "go-hexagonal-starter/internal/app"
    "go-hexagonal-starter/internal/infrastructure/db"
    "github.com/gofiber/fiber/v2"
)

func main() {
    gymRepo := db.NewGymRepositoryInMemory()

    gymService := app.NewGymService(gymRepo)

    app := fiber.New()


    app.Get("/gimnasios/:id/horarios", func(c *fiber.Ctx) error {
        gymID := c.Params("id")
        schedules, err := gymService.GetGymSchedules(gymID)
        if err != nil {
            return c.Status(404).SendString("Gimnasio no encontrado")
        }
        return c.JSON(schedules)
    })

    if err := app.Listen(":3000"); err != nil {
        fmt.Println("Error al iniciar el servidor:", err)
    }
}
