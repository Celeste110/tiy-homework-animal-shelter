import java.util.Scanner;

/**
 * Created by katherine_celeste on 8/16/16.
 * This class displays menus, prompts the user for input, and returns results.
 */

public class MenuService {
    private static final int LIST_ANIMALS = 1;
    private static final int CREATE_ANIMAL = 2;
    private static final int VIEW_ANIMAL_DETAILS = 3;
    private static final int EDIT_ANIMAL = 4;
    private static final int DELETE_ANIMAL = 5;
    private static final int EXIT_PROGRAM = 6;

    public static int promptForMainMenuSelection() {
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

    public static int waitForInt(String prompt) {

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

    public static boolean performRequestedAction(int userSelection) {
        switch (userSelection) {
            case LIST_ANIMALS:
                AnimalsService.displayAnimalsList();
                break;

            case CREATE_ANIMAL:
                promptForAnimalData();
                break;

            case VIEW_ANIMAL_DETAILS:
                AnimalsService.getAnimal();
                break;

            case EDIT_ANIMAL:
                AnimalsService.editAnimal();
                break;

            case DELETE_ANIMAL:
                AnimalsService.deleteAnimal();
                break;

            case EXIT_PROGRAM:
                if (confirmExit() == false) {
                    return false;
                }
        }
        return true;
    }


    public static void promptForAnimalData() {
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

        //create an Animal instance and add the new animal to an ArrayList
        AnimalsService.createAnimal(name, species, breed, description);

        System.out.println("\nSuccess: The animal has been created!");

    }

    public static boolean isInteger(String userInput) {
        boolean isAnInt = false;
        try {
            Integer.parseInt(userInput);

            isAnInt = true;
        } catch (NumberFormatException ex) {
            // the user did not input a valid integer!!!
        }

        return isAnInt;
    }

    public static String retrieveAnimalInfoFromUser(String animalProperty) {

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

    public static String validateAndSetUserInputForAnimal(String animalProperty, String currentAnimalProperty) {
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

    public static boolean confirmExit() {
        Scanner reader = new Scanner(System.in);
        System.out.print("Are you sure you want to quit? All of your data will be lost! (Yes/No): ");
        String readString = reader.nextLine();
        boolean flag = true;
        while (flag)
            if (readString.equalsIgnoreCase("yes")) {
                System.out.println("\nGoodbye!");
                return false;
            } else if (readString.equalsIgnoreCase("no")) {
                int newUserSelection = MenuService.promptForMainMenuSelection(); // returns user selection
                MenuService.performRequestedAction(newUserSelection); // returns true/false to indicate if menu should display again
                flag = false;
            } else {
                System.out.println("Error: Sorry, that isn't a valid option.");
            }
        return true;
    }

    public static boolean confirmDelete(int index) {
        System.out.print("\nAre you sure you want to delete this animal? (Yes/No): ");

        Scanner reader = new Scanner(System.in);
        String readString = reader.nextLine();

        while (true)
            if (readString.equalsIgnoreCase("yes")) {
                AnimalsService.removeAnAnimal(index);
                System.out.println("\nSuccess: The animal has been deleted!");
                return false;
            } else if (readString.equalsIgnoreCase("no")) {
                AnimalsService.deleteAnimal();
                return true;
            } else {
                System.out.println("Error: Sorry, that isn't a valid option.");
            }
    }
}
