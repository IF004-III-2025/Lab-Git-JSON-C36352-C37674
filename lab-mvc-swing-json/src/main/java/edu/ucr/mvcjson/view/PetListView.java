package edu.ucr.mvcjson.view;

import edu.ucr.mvcjson.model.Pet;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PetListView extends JFrame {

    public PetListView(List<Pet> pets) {

        setTitle("Registered Pets");
        setSize(500, 300);
        setLocationRelativeTo(null);

        String[] columns = {"Name", "Species", "Age", "Owner Phone"};
        Object[][] data = new Object[pets.size()][4];

        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);
            data[i][0] = pet.getName();
            data[i][1] = pet.getSpecies();
            data[i][2] = pet.getAge();
            data[i][3] = pet.getOwnerPhone();
        }

        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}