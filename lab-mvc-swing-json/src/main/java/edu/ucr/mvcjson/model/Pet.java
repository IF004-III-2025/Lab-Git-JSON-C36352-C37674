package edu.ucr.mvcjson.model;

public class Pet {
    private String name;
    private String species;
    private int age;
    private String ownerPhone;

    public Pet(String name, String species, int age, String ownerPhone) {
        //se usan los setters por algo llamado DRY (Don't Repeat Yourself), las validaciones quedan en los setters y no se tienen q repetir en el constructor
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
            throw new IllegalArgumentException("El nombre de la mascota no puede ser null o vacío.");
        }
        this.name = name.trim();
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        if (species == null || species.trim().isEmpty()) {
            throw new IllegalArgumentException("La especie de la mascota no puede ser null o vacía.");
        }

        String normalizedSpecies = species.trim().toUpperCase();
        if (!isValidSpecies(normalizedSpecies)) {
            throw new IllegalArgumentException(
                    "La especie '" + species + "' no es válida. Especies válidas: DOG, CAT, BIRD, RABBIT, OTHER."
            );
        }

        this.species = normalizedSpecies;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("La edad de la mascota no puede ser negativa.");
        }
        this.age = age;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        if (ownerPhone == null || ownerPhone.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de teléfono del dueño no puede ser null o vacío.");
        }

        String cleanPhone = ownerPhone.trim();

        if (cleanPhone.length() != 8) {
            throw new IllegalArgumentException("El número de teléfono del dueño debe tener exactamente 8 dígitos.");
        }

        if (!cleanPhone.matches("\\d{8}")) {
            throw new IllegalArgumentException("El número de teléfono debe contener solo dígitos.");
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