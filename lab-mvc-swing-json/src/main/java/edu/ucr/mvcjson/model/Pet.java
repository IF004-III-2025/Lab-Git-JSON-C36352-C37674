package edu.ucr.mvcjson.model;

public class Pet {
    private String name;
    private String species;
    private int age;
    private String ownerPhone;

    public Pet(String name, String species, int age, String ownerPhone) {
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la mascota no puede ser null o vacío.");
        }

        if(species == null || species.isEmpty()) {
            throw new IllegalArgumentException("La especie de la mascota no puede ser null o vacía.");
        }

        if(age >= 0) {
            throw new IllegalArgumentException("La edad de la mascota no puede ser negativa.");
        }

        if(ownerPhone == null || ownerPhone.isEmpty()) {
            throw new IllegalArgumentException("El número de teléfono del dueño no puede ser null o vacío.");
        } else if(ownerPhone.length() != 8) {
            throw new IllegalArgumentException("El número de teléfono del dueño debe tener exactamente 8 dígitos.");
        }



        
        this.name = name;
        this.species = species;
        this.age = age;
        this.ownerPhone = ownerPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }
}
