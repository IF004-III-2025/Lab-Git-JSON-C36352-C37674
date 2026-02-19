package edu.ucr.mvcjson.repository;

import edu.ucr.mvcjson.model.Pet;

import java.io.IOException;
import java.util.List;

public interface PetRepository {

    void save(Pet pet) throws IOException;
    List<Pet> findAll();
    void loadFromFile();
    List<Pet> findByOwnerPhone(String name);

}
