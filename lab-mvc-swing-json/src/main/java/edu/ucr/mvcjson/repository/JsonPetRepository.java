package edu.ucr.mvcjson.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ucr.mvcjson.model.Pet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonPetRepository implements  PetRepository {

    private String filePath;
    private ObjectMapper mapper;
    private File file;
    private List<Pet> pets;

    public JsonPetRepository(String filePath) {
        this.filePath = filePath;
        this.mapper = new ObjectMapper();
        this.file = new File(filePath);
        this.pets = new ArrayList<>();

        //crea el directorio si este no existe
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        //crea un archivo vacio si no existe archivo
        if(!file.exists()) {
            try {
                file.createNewFile();
                mapper.writeValue(file, pets);
            } catch (IOException e) {
                throw new RuntimeException("Could not create file: " + filePath, e);
            }
        }

        loadFromFile();
    }

    @Override
    public void save(Pet pet) throws IOException {
        if(pet == null) {
            throw new IllegalArgumentException("Pet cannot be null");
        }

        pets.add(pet);
        writeToFile();
    }

    @Override
    public List<Pet> findAll() {
        return List.copyOf(pets);
    }

    @Override
    public void loadFromFile() {
        try{
           if(file.length() == 0) {
               this.pets = new ArrayList<>();
               return;
           }
           this.pets = mapper.readValue(file, new TypeReference<List<Pet>>() {});

           if(this.pets == null) {
               this.pets = new ArrayList<>();
           }
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar mascotas desde: " + filePath, e);
        }
    }

    @Override
    public List<Pet> findByOwnerPhone(String phone) {
        if (phone == null) {
            return new ArrayList<>();
        }

        return pets.stream().filter(p -> p.getOwnerPhone().equals(phone)).toList();
    }

    private void writeToFile() throws IOException {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, pets);
        } catch (IOException e) {
            throw new IOException("Error al guardar mascotas en: " + filePath, e);
        }
    }
}
