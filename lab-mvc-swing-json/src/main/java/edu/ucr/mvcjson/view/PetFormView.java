package edu.ucr.mvcjson.view;

import edu.ucr.mvcjson.controller.PetController;
import edu.ucr.mvcjson.model.Pet;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PetFormView extends JFrame {

    private final PetController controller;

    private JTextField nameField;
    private JComboBox<String> speciesCombo;
    private JTextField ageField;
    private JTextField ownerPhoneField;

    private JButton saveButton;
    private JButton listButton;

    public PetFormView(PetController controller) {
        this.controller = controller;

        setTitle("Pet Registration");
        setSize(400, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));

        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Species:"));
        speciesCombo = new JComboBox<>(new String[]{
                "DOG", "CAT", "BIRD", "RABBIT", "OTHER"
        });
        panel.add(speciesCombo);

        panel.add(new JLabel("Age:"));
        ageField = new JTextField();
        panel.add(ageField);

        panel.add(new JLabel("Owner Phone:"));
        ownerPhoneField = new JTextField();
        panel.add(ownerPhoneField);

        saveButton = new JButton("Save");
        listButton = new JButton("List Pets");

        panel.add(saveButton);
        panel.add(listButton);

        add(panel);

        saveButton.addActionListener(e -> savePet());
        listButton.addActionListener(e -> openListWindow());
    }

    private void savePet() {
        try {
            String name = nameField.getText();
            String species = (String) speciesCombo.getSelectedItem();
            int age = Integer.parseInt(ageField.getText());
            String ownerPhone = ownerPhoneField.getText();

            controller.addPet(name, species, age, ownerPhone);

            JOptionPane.showMessageDialog(this,
                    "Pet saved successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            clearFields();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Age must be a valid number.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        } catch (IllegalArgumentException | IOException ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openListWindow() {
        List<Pet> pets = controller.getAllPets();
        new PetListView(pets);
    }

    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        ownerPhoneField.setText("");
        speciesCombo.setSelectedIndex(0);
    }
}