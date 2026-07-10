package pe.edu.petnimals.service;

import pe.edu.petnimals.model.Animal;
import java.util.List;

public interface AnimalService {

    List<Animal> findAll();

    Animal save(Animal animal);

    void deleteById(Long id);

    public Animal guardarAnimal(Animal nuevoAnimal);
}

