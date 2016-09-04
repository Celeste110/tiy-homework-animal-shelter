package service;

import entity.Animal;
import entity.AnimalNotes;
import repository.AnimalRepository;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by katherine_celeste on 8/16/16.
 */
public class AnimalsService {

    private AnimalRepository animalRepository;
    private NoteService noteService;
    private TypeService typeService;

    public AnimalsService(AnimalRepository animalRepository, NoteService noteService, TypeService typeService) {
        this.animalRepository = animalRepository;
        this.noteService = noteService;
        this.typeService = typeService;
    }

    public NoteService getNoteService() {
        return noteService;
    }

    public TypeService getTypeService() {
        return typeService;
    }

    public ArrayList<Animal> listAnimals() {
        ArrayList<Animal> animals = new ArrayList<>();

        try {
            ResultSet resultSet = this.animalRepository.listAnimal();

            while (resultSet.next()) {
                Animal anAnimal = new Animal(
                        resultSet.getString("animal_name"),
                        resultSet.getInt("animal_type_id"),
                        resultSet.getString("breed"),
                        resultSet.getString("description"),
                        resultSet.getInt("animal_id"),
                        noteService.listNotes(),
                        this.getTypeService()
                );
                animals.add(anAnimal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return animals;
    }

    public void createAnimal(String name, int species, String breed, String description, int ID, ArrayList<AnimalNotes> notes) throws IOException, SQLException {
        animalRepository.createAnimal(new Animal(name, species, breed, description, ID, notes, typeService));
    }

    public void removeAnAnimal(int index) throws SQLException {
        animalRepository.removeAnimal(index);
    }

    public Animal getAnimalById(int index) throws SQLException {
        Animal placeHolder = new Animal();
        for (Animal animal : listAnimals()) {
            if (animal.getID() == index) {
                return animal;
            }
        }
        System.out.println("\nThere is no animal with that ID. Please try again.");

        return placeHolder;
    }

    public ArrayList<Animal> getAnimalsByType(int type, NoteService n) throws SQLException {
        return animalRepository.getAnimalsByType(type, n);
    }

    public ArrayList<Animal> getAnimalsByName(String type, NoteService n) throws SQLException {
        return animalRepository.getAnimalsByName(type, n);
    }

    public void modifyAnimal(String property, String newInput, Animal animal) throws SQLException {
        try {
            int typeID = -1;
            if (property.equals("species")) {
                typeID = typeService.getTypeID(newInput);
                animalRepository.modifyAnimal(property, newInput, animal, typeID);
            } else
                animalRepository.modifyAnimal(property, newInput, animal, typeID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}