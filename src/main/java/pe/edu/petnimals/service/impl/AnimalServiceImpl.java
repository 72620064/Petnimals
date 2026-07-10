package pe.edu.petnimals.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.petnimals.model.Animal;
import pe.edu.petnimals.repository.AnimalRepository;
import pe.edu.petnimals.service.AnimalService;

import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository repository;

    @Override
    public List<Animal> findAll() {
        return repository.findAll();
    }

    @Override
    public Animal save(Animal animal) {
        return repository.save(animal);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Animal guardarAnimal(Animal nuevoAnimal) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}