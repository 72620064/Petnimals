package pe.edu.petnimals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.petnimals.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}