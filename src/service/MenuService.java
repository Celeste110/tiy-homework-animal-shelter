package service;
import entity.Animal;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by katherine_celeste on 8/16/16.
 * This class displays menus, prompts the user for input, and returns results.
 */

public class MenuService {
    public static final int LIST_ANIMALS = 1;
    public static final int CREATE_ANIMAL = 2;
    public static final int VIEW_ANIMAL_DETAILS = 3;
    public static final int EDIT_ANIMAL = 4;
    public static final int DELETE_ANIMAL = 5;
    public static final int EXIT_PROGRAM = 6;

    AnimalsService service;

    public MenuService(AnimalsService service)
    {
        this.service = service;
    }


    public int promptForMainMenuSelection() {
        System.out.println("\n-- Main Menu --\n" +
                "\n" +
                "1) List animals\n" +
                "2) Create an animal\n" +
                "3) View animal details\n" +
                "4) Edit an animal\n" +
                "5) Delete an animal\n" +
                "6) Quit\n");

        return waitForInt("Please choose an option: ");
    }

    public int waitForInt(String prompt) {

        System.out.print(prompt);

        Scanner reader = new Scanner(System.in);

        Integer aNum = null;

        while (aNum == null) {

            if (reader.hasNextInt()) {
                aNum = reader.nextInt();
                System.out.println();
            } else { // if user inputs random text...
                String discardInput = reader.nextLine();
                System.out.println("\nError: Sorry, that isn't a valid option.");
                System.out.print("\n" + prompt);
            }
        }
        return aNum;
    }


    public void displayAnimalsList() {

        if (service.listAnimals().size() == 0) {
            System.out.println("You haven't added any animals yet!\n\nSelect option 2 to add an animal to the list.");
        } else {

            int counter = 0;
            System.out.println("-- List of Animals --\n");

            while (counter < service.listAnimals().size()) {
                String name = service.listAnimals().get(counter).getName();
                String type = service.listAnimals().get(counter).getSpecies();
                System.out.printf("%s) %s \t %s\n", ++counter, name, type);
            }
            System.out.println();
        }
    }


    public void promptForAnimalData() {
        String name, species, breed, description;

        System.out.println("-- Create an Animal --");
        System.out.println("\nPlease answer the following questions.");

        System.out.print("\nAnimal Name: ");
        name = retrieveAnimalInfoFromUser("entity.Animal Name");

        System.out.print("Species: ");
        species = retrieveAnimalInfoFromUser("Species");

        System.out.print("Breed (optional): ");
        breed = retrieveAnimalInfoFromUser("Breed (optional)");

        System.out.print("Description: ");
        description = retrieveAnimalInfoFromUser("Description");

        System.out.println("\nSuccess: The animal has been created!");
        //create an entity.Animal instance and add the new animal to an ArrayList
        service.createAnimal(name, species, breed, description);

    }

    public boolean isInteger(String userInput) {
        boolean isAnInt = false;
        try {
            Integer.parseInt(userInput);

            isAnInt = true;
        } catch (NumberFormatException ex) {
            // the user did not input a valid integer!!!
        }

        return isAnInt;
    }

    public String retrieveAnimalInfoFromUser(String animalProperty) {

        Scanner reader = new Scanner(System.in);
        String readString = reader.nextLine();
        Boolean flag = true;

        while (flag) {

            if (animalProperty.equals("Breed (optional)")) // Since this is optional, don't need to check for enter key
            {
                if (isInteger(readString)) {
                    System.out.printf("\nError: Please enter a non-numeric value for this field.\n");
                    System.out.printf("\n%s: ", animalProperty);
                    readString = reader.next();
                } else {
                    flag = false; // exit loop
                }
            } else if ((readString.isEmpty())) //checks to see if user pressed enter
            {
                System.out.printf("\nError: The %s field is required. Please try again.\n", animalProperty);
                System.out.printf("\n%s: ", animalProperty);
                readString = reader.next();
            } else if (isInteger(readString)) {
                System.out.printf("Error: Please enter a non-numeric value for this field: ");
                readString = reader.next();
            } else {
                flag = false; // exit loop
            }
        }
        return readString; // return userInput
    }

    public String validateAndSetUserInputForAnimal(String animalProperty, String currentAnimalProperty) {
        Scanner reader = new Scanner(System.in);
        String readString = reader.nextLine();
        Boolean flag = true;

        while (flag) {

            if (isInteger(readString)) //checks to see if user entered a number instead of text
            {
                System.out.printf("Error: Please enter a non-numeric value for this field.");
                System.out.printf("\n%s: ", animalProperty);
                readString = reader.next();
            } else if (readString.isEmpty()) {
                // keeps the current value. do nothing here
                return currentAnimalProperty;
            } else {
                return readString;
            }
        }
        return readString;
    }

    public boolean confirmExit() {
        Scanner reader = new Scanner(System.in);
        System.out.print("Are you sure you want to quit? (Yes/No): ");
        String readString = reader.nextLine();

        if (readString.equalsIgnoreCase("yes")) {
            System.out.println("\nGoodbye!");
            return false;
        } else if (readString.equalsIgnoreCase("no")) {
            return true;
        } else {
            System.out.println("\nError: Sorry, that isn't a valid option. Must type \"Yes\" or \"No\"");
            System.out.println("Please review the menu options below and select an option again.");
            return true;
        }
    }

    public boolean confirmDelete(int index) {
        System.out.print("\nAre you sure you want to delete this animal? (Yes/No): ");

        Scanner reader = new Scanner(System.in);
        String readString = reader.nextLine();

        if (readString.equalsIgnoreCase("yes")) {
            service.removeAnAnimal(index);
            System.out.println("\nSuccess: The animal has been deleted!");
            return false;
        } else if (readString.equalsIgnoreCase("no")) {
            deleteAnimal();
            return true;
        } else {
            System.out.println("\nError: Sorry, that isn't a valid option. Must type \"Yes\" or \"No\".");
            System.out.println("Please review the menu options below and select an option again.");
            return true;
        }
    }

    // Retrieves an animal from a position on the list and prints the info to the console
    public void getAnimal() {
        System.out.println("-- View an Animal --\n");
        int userSelection = waitForInt("What is the numeric ID of the animal you want to view?: ") - 1; // subtract 1 (due to zero-indexed ArrayList)

        ArrayList<Animal> animals = service.listAnimals();

        if (userSelection <= animals.size() - 1 && userSelection >= 0) {
            System.out.println("\nName: " + service.getAnimal(userSelection).getName());
            System.out.println("Species: " + service.getAnimal(userSelection).getSpecies());
            System.out.println("Breed (optional): " + service.getAnimal(userSelection).getBreed());
            System.out.println("Description: " + service.getAnimal(userSelection).getDescription());
        } else {
            System.out.printf("You haven't added enough animals! You currently have %s animals in the list.\n", service.listAnimals().size());
            System.out.println("\nReturning you to the main menu so you can add more animals (or select another option...");
        }
    }

    public void editAnimal() {
        System.out.println("-- Edit an Animal --");
        int userSelection = waitForInt("\nWhat is the numeric ID of the animal you want to edit?: ") - 1; // subtract 1 (due to zero-indexed ArrayList)
        String userInput;

        if (userSelection <= service.listAnimals().size() - 1 && userSelection >= 0) {
            System.out.println("Please answer the following questions. Press enter to keep the current value.");
            System.out.printf("\nAnimal Name [%s]: ", service.getAnimal(userSelection).getName());
            userInput = validateAndSetUserInputForAnimal("Enter non-numeric Animal Name: ", service.getAnimal(userSelection).getName());
            service.modifyAnimal(userSelection, "name", userInput );

            System.out.printf("Species [%s]: ", service.getAnimal(userSelection).getSpecies());
            userInput = validateAndSetUserInputForAnimal("Enter non-numeric Species: ", service.getAnimal(userSelection).getSpecies());
            service.modifyAnimal(userSelection, "species", userInput );

            System.out.printf("Breed (optional) [%s]: ", service.getAnimal(userSelection).getBreed());
            userInput = validateAndSetUserInputForAnimal("Enter non-numeric Breed (optional): ", service.getAnimal(userSelection).getBreed());
            service.modifyAnimal(userSelection, "breed", userInput );

            System.out.printf("Description [%s]: ", service.getAnimal(userSelection).getDescription());
            userInput = validateAndSetUserInputForAnimal("Enter non-numeric Description: ", service.getAnimal(userSelection).getDescription());
            service.modifyAnimal(userSelection, "description", userInput );

            System.out.println("\nSuccess: The animal has been updated!\n");

            System.out.println("Name: " + service.getAnimal(userSelection).getName());
            System.out.println("Species: " + service.getAnimal(userSelection).getSpecies());
            System.out.println("Breed: " + service.getAnimal(userSelection).getBreed());
            System.out.println("Description: " + service.getAnimal(userSelection).getDescription());
        } else {
            System.out.printf("You haven't added enough animals! You currently have %s animals in the list.\n", service.listAnimals().size());
            System.out.println("\nReturning you to the main menu so you can add more animals (or select another option...");
        } //***NEED TO SAVE CHANGES TO DISK****
    }

    public void deleteAnimal() {
        int userSelection = waitForInt("What is the numeric ID of the animal you want to delete?: ") - 1; // subtract 1 (due to zero-indexed ArrayList)

        if (userSelection <= service.listAnimals().size() - 1 && userSelection >= 0) {
            System.out.println("Name: " + service.getAnimal(userSelection).getName());
            System.out.println("Species: " + service.getAnimal(userSelection).getSpecies());
            System.out.println("Breed: " + service.getAnimal(userSelection).getBreed());
            System.out.println("Description: " + service.getAnimal(userSelection).getDescription());
            confirmDelete(userSelection);
        } else {
            System.out.printf("You haven't added enough animals! You currently have %s animals in the list.\n", service.listAnimals().size());
            System.out.println("\nReturning you to the main menu so you can add more animals (or select another option...");
        }
    }
}
