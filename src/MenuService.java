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
                if (aNum < 1 || aNum > 6)  // check to see if a valid option was entered
                {
                    String discardInput = reader.nextLine();
                    System.out.println("Error: Sorry, that isn't a valid option.");
                    System.out.print("\nPlease choose an option: ");
                    aNum = null;
                }
            } else { // if user inputs random text...
                String discardInput = reader.nextLine();
                System.out.println("\nError: Sorry, that isn't a valid option.");
                System.out.print("\n" + prompt);
            }
        }
        return aNum;
    }


    public void displayAnimalsList(AnimalsService service) {

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


    public void promptForAnimalData(AnimalsService service) {
        String name, species, breed, description;

        System.out.println("-- Create an Animal --");
        System.out.println("\nPlease answer the following questions.");

        System.out.print("\nAnimal Name: ");
        name = retrieveAnimalInfoFromUser("Animal Name");

        System.out.print("Species: ");
        species = retrieveAnimalInfoFromUser("Species");

        System.out.print("Breed (optional): ");
        breed = retrieveAnimalInfoFromUser("Breed (optional)");

        System.out.print("Description: ");
        description = retrieveAnimalInfoFromUser("Description");

        System.out.println("\nSuccess: The animal has been created!");
        //create an Animal instance and add the new animal to an ArrayList
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
        System.out.print("Are you sure you want to quit? All of your data will be lost! (Yes/No): ");
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

    public boolean confirmDelete(int index, AnimalsService service, MenuService menu) {
        System.out.print("\nAre you sure you want to delete this animal? (Yes/No): ");

        Scanner reader = new Scanner(System.in);
        String readString = reader.nextLine();

        if (readString.equalsIgnoreCase("yes")) {
            service.removeAnAnimal(index);
            System.out.println("\nSuccess: The animal has been deleted!");
            return false;
        } else if (readString.equalsIgnoreCase("no")) {
            deleteAnimal(service, menu);
            return true;
        } else {
            System.out.println("\nError: Sorry, that isn't a valid option. Must type \"Yes\" or \"No\".");
            System.out.println("Please review the menu options below and select an option again.");
            return true;
        }
    }

    // Retrieves an animal from a position on the list and prints the info to the console
    public void getAnimal(AnimalsService service, MenuService menu) {
        System.out.println("-- View an Animal --\n");
        int userSelection = menu.waitForInt("What is the numeric ID of the animal you want to view?: ") - 1; // subtract 1 (due to zero-indexed ArrayList)

        if (userSelection <= service.listAnimals().size() - 1 && userSelection >= 0) {
            System.out.println("\nName: " + service.listAnimals().get(userSelection).getName());
            System.out.println("Species: " + service.listAnimals().get(userSelection).getSpecies());
            System.out.println("Breed (optional):" + service.listAnimals().get(userSelection).getBreed());
            System.out.println("Description: " + service.listAnimals().get(userSelection).getDescription());
        } else {
            System.out.printf("You haven't added enough animals! You currently have %s animals in the list.\n", service.listAnimals().size());
            System.out.println("\nReturning you to the main menu so you can add more animals (or select another option...");
        }
    }

    public void editAnimal(AnimalsService service, MenuService menu) {
        System.out.println("-- Edit an Animal --");
        int userSelection = menu.waitForInt("\nWhat is the numeric ID of the animal you want to edit?: ") - 1; // subtract 1 (due to zero-indexed ArrayList)
        String userInput;

        System.out.println("Please answer the following questions. Press enter to keep the current value.");

        if (userSelection <= service.listAnimals().size() - 1 && userSelection >= 0) {
            System.out.printf("\nAnimal Name [%s]: ", service.listAnimals().get(userSelection).getName());
            userInput = menu.validateAndSetUserInputForAnimal("Enter non-numeric Animal Name: ", service.listAnimals().get(userSelection).getName());
            service.listAnimals().get(userSelection).setName(userInput);

            System.out.printf("Species [%s]: ", service.listAnimals().get(userSelection).getSpecies());
            userInput = menu.validateAndSetUserInputForAnimal("Enter non-numeric Species: ", service.listAnimals().get(userSelection).getSpecies());
            service.listAnimals().get(userSelection).setSpecies(userInput);

            System.out.printf("Breed (optional) [%s]: ", service.listAnimals().get(userSelection).getBreed());
            userInput = menu.validateAndSetUserInputForAnimal("Enter non-numeric Breed (optional): ", service.listAnimals().get(userSelection).getBreed());
            service.listAnimals().get(userSelection).setBreed(userInput);

            System.out.printf("Description [%s]: ", service.listAnimals().get(userSelection).getDescription());
            userInput = menu.validateAndSetUserInputForAnimal("Enter non-numeric Description: ", service.listAnimals().get(userSelection).getDescription());
            service.listAnimals().get(userSelection).setDescription(userInput);

            System.out.println("\nSuccess: The animal has been updated!\n");

            System.out.println("Name: " + service.listAnimals().get(userSelection).getName());
            System.out.println("Species: " + service.listAnimals().get(userSelection).getSpecies());
            System.out.println("Breed: " + service.listAnimals().get(userSelection).getBreed());
            System.out.println("Description: " + service.listAnimals().get(userSelection).getDescription());
        } else {
            System.out.printf("You haven't added enough animals! You currently have %s animals in the list.\n", service.listAnimals().size());
            System.out.println("\nReturning you to the main menu so you can add more animals (or select another option...");
        }
    }

    public void deleteAnimal(AnimalsService service, MenuService menu) {
        int userSelection = menu.waitForInt("What is the numeric ID of the animal you want to delete?: ") - 1; // subtract 1 (due to zero-indexed ArrayList)

        if (userSelection <= service.listAnimals().size() - 1 && userSelection >= 0) {
            System.out.println("Name: " + service.listAnimals().get(userSelection).getName());
            System.out.println("Species: " + service.listAnimals().get(userSelection).getSpecies());
            System.out.println("Breed: " + service.listAnimals().get(userSelection).getBreed());
            System.out.println("Description: " + service.listAnimals().get(userSelection).getDescription());
            menu.confirmDelete(userSelection, service, menu);
        } else {
            System.out.printf("You haven't added enough animals! You currently have %s animals in the list.\n", service.listAnimals().size());
            System.out.println("\nReturning you to the main menu so you can add more animals (or select another option...");
        }
    }
}
