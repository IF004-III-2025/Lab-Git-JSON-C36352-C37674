package edu.ucr.mvcjson.controller;

import edu.ucr.mvcjson.repository.PetRepository;

public class PetController {
    private PetRepository petRepository;

    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public void addPet(String name, String species, int age, String ownerPhone) {
        
    }

}
