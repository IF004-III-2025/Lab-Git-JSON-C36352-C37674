package edu.ucr.mvcjson.app;

import edu.ucr.mvcjson.model.Pet;
import edu.ucr.mvcjson.repository.JsonPetRepository;

import java.io.IOException;

public class Main {
    static void main() throws IOException {
        String playerFilePath = "data/pets.json";
        JsonPetRepository jsonPetRepository = new JsonPetRepository(playerFilePath);

        Pet pet1 = new Pet("Buddy", "Dog", 5, "12345678");

        jsonPetRepository.save(pet1);

    }
}
