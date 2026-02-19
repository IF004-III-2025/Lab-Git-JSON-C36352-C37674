package edu.ucr.mvcjson.controller;

import edu.ucr.mvcjson.model.Pet;
import edu.ucr.mvcjson.repository.PetRepository;

import java.io.IOException;
import java.util.List;

public class PetController {
    private PetRepository petRepository;

    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public void addPet(String name, String species, int age, String ownerPhone) throws IOException {
        validatePetData(name, species, age, ownerPhone);
        try{
            Pet newPet = new Pet(name, species, age, ownerPhone);
            petRepository.save(newPet);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Error al crear la mascota: " + e.getMessage());
        }
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public List<Pet> searchByOwnerPhone(String phone) {
        if (!isValidPhone(phone)) {
            throw new IllegalArgumentException("El teléfono debe tener exactamente 8 dígitos");
        }
        return petRepository.findByOwnerPhone(phone);
    }

    private void validatePetData(String name, String species, int age, String ownerPhone) {
        if (!isValidName(name)) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (species == null || species.trim().isEmpty()) {
            throw new IllegalArgumentException("La especie no puede estar vacía");
        }
        if (!isValidAge(age)) {
            throw new IllegalArgumentException("La edad no puede ser negativa");
        }
        if (!isValidPhone(ownerPhone)) {
            throw new IllegalArgumentException("El teléfono debe tener exactamente 8 dígitos");
        }
    }

    private boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    private boolean isValidAge(int age) {
        return age >= 0;
    }

    private boolean isValidPhone(String phone) {
        return phone != null &&
                phone.trim().length() == 8 &&
                phone.matches("\\d{8}"); //verifica que solo tenga números
    }
}