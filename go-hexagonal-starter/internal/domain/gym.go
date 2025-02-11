package domain

type Gym struct {
	ID       string   `json:"id"`
	Name     string   `json:"name"`
	Location string   `json:"location"`
	Horarios []string `json:"horarios"`
}
