package edu.ucr.mvcjson.app;

import edu.ucr.mvcjson.controller.PetController;
import edu.ucr.mvcjson.repository.JsonPetRepository;
import edu.ucr.mvcjson.repository.PetRepository;
import edu.ucr.mvcjson.view.PetFormView;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            String filePath = "data/pets.json";

            PetRepository repository = new JsonPetRepository(filePath);
            PetController controller = new PetController(repository);

            PetFormView view = new PetFormView(controller);
            view.setVisible(true);
        });
    }
}