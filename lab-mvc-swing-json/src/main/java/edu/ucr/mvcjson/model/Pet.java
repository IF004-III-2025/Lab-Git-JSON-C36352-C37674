package edu.ucr.mvcjson.model;

public class Pet {

    private String name;
    private String species;
    private int age;
    private String ownerPhone;

    // //Según lo investigado Jackson requiere el constructor vacío para la deserialización
    public Pet() {
    }

    public Pet(String name, String species, int age, String ownerPhone) {
        setName(name);
        setSpecies(species);
        setAge(age);
        setOwnerPhone(ownerPhone);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("The pet name cannot be null or empty.");
        }
        this.name = name.trim();
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        if (species == null || species.trim().isEmpty()) {
            throw new IllegalArgumentException("The pet species cannot be null or empty.");
        }

        String normalizedSpecies = species.trim().toUpperCase();
        if (!isValidSpecies(normalizedSpecies)) {
            throw new IllegalArgumentException(
                    "Invalid species '" + species + "'. Valid species: DOG, CAT, BIRD, RABBIT, OTHER."
            );
        }

        this.species = normalizedSpecies;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("The pet age cannot be negative.");
        }
        this.age = age;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        if (ownerPhone == null || ownerPhone.trim().isEmpty()) {
            throw new IllegalArgumentException("The owner phone cannot be null or empty.");
        }

        String cleanPhone = ownerPhone.trim();

        if (cleanPhone.length() != 8) {
            throw new IllegalArgumentException("The owner phone must have exactly 8 digits.");
        }

        if (!cleanPhone.matches("\\d{8}")) {
            throw new IllegalArgumentException("The owner phone must contain only digits.");
        }

        this.ownerPhone = cleanPhone;
    }

    private boolean isValidSpecies(String species) {
        try {
            Species.valueOf(species.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", age=" + age +
                ", ownerPhone='" + ownerPhone + '\'' +
                '}';
    }
}