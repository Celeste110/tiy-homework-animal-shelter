import java.util.ArrayList;

/**
 * Created by katherine_celeste on 8/16/16.
 */
public class AnimalsService {
    private static ArrayList<Animal> animals = new ArrayList<>();

    public static void removeAnAnimal(int index) {
        animals.remove(index);
    }

    public static void displayAnimalsList() {

        if (animals.size() == 0) {
            System.out.println("You haven't added any animals yet!\n\nSelect option 2 to add an animal to the list.");
        } else {

            int counter = 0;
            System.out.println("-- List of Animals --\n");

            while (counter < animals.size()) {
                String name = animals.get(counter).getName();
                String type = animals.get(counter).getSpecies();
                System.out.printf("%s) %s \t %s\n", ++counter, name, type);
            }
            System.out.println();
        }
    }

    // Retrieves an animal from a position on the list and prints the info to the console
    public static void getAnimal() {
        System.out.println("-- View an Animal --\n");
        int userSelection = MenuService.waitForInt("What is the numeric ID of the animal you want to view?: ") - 1; // subtract 1 (due to zero-indexed ArrayList)

        if (userSelection <= animals.size() - 1 && userSelection >= 0) {
            System.out.println("\nName: " + animals.get(userSelection).getName());
            System.out.println("Species: " + animals.get(userSelection).getSpecies());
            System.out.println("Breed (optional):" + animals.get(userSelection).getBreed());
            System.out.println("Description: " + animals.get(userSelection).getDescription());
        } else {
            System.out.printf("You haven't added enough animals! You currently have %s animals in the list.\n", animals.size());
            System.out.println("\nReturning you to the main menu so you can add more animals (or select another option...");
            int newUserSelection = MenuService.promptForMainMenuSelection(); // returns user selection
            MenuService.performRequestedAction(newUserSelection); // returns true/false to indicate if menu should display again
        }
    }

    public static void editAnimal() {
        System.out.println("-- Edit an Animal --");
        int userSelection = MenuService.waitForInt("\nWhat is the numeric ID of the animal you want to edit?: ") - 1; // subtract 1 (due to zero-indexed ArrayList)
        String userInput;

        System.out.println("Please answer the following questions. Press enter to keep the current value.");

        if (userSelection <= animals.size() - 1 && userSelection >= 0) {
            System.out.printf("\nAnimal Name [%s]: ", animals.get(userSelection).getName());
            userInput = MenuService.validateAndSetUserInputForAnimal("Enter non-numeric Animal Name: ", animals.get(userSelection).getName());
            animals.get(userSelection).setName(userInput);

            System.out.printf("Species [%s]: ", animals.get(userSelection).getSpecies());
            userInput = MenuService.validateAndSetUserInputForAnimal("Enter non-numeric Species: ", animals.get(userSelection).getSpecies());
            animals.get(userSelection).setSpecies(userInput);

            System.out.printf("Breed (optional) [%s]: ", animals.get(userSelection).getBreed());
            userInput = MenuService.validateAndSetUserInputForAnimal("Enter non-numeric Breed (optional): ", animals.get(userSelection).getBreed());
            animals.get(userSelection).setBreed(userInput);

            System.out.printf("Description [%s]: ", animals.get(userSelection).getDescription());
            userInput = MenuService.validateAndSetUserInputForAnimal("Enter non-numeric Description: ", animals.get(userSelection).getDescription());
            animals.get(userSelection).setDescription(userInput);

            System.out.println("\nSuccess: The animal has been updated!\n");

            System.out.println("Name: " + animals.get(userSelection).getName());
            System.out.println("Species: " + animals.get(userSelection).getSpecies());
            System.out.println("Breed: " + animals.get(userSelection).getBreed());
            System.out.println("Description: " + animals.get(userSelection).getDescription());
        } else {
            System.out.printf("You haven't added enough animals! You currently have %s animals in the list.\n", animals.size());
            System.out.println("\nReturning you to the main menu so you can add more animals (or select another option...");
            int newUserSelection = MenuService.promptForMainMenuSelection(); // returns user selection
            MenuService.performRequestedAction(newUserSelection); // returns true/false to indicate if menu should display again
        }
    }

    // Creates a new Animal and adds it to the list
    public static void createAnimal(String name, String species, String breed, String description) {
        animals.add(new Animal(name, species, breed, description));
    }

    public static void deleteAnimal() {
        int userSelection = MenuService.waitForInt("What is the numeric ID of the animal you want to delete?: ") - 1; // subtract 1 (due to zero-indexed ArrayList)

        if (userSelection <= animals.size() - 1 && userSelection >= 0) {
            System.out.println("Name: " + animals.get(userSelection).getName());
            System.out.println("Species: " + animals.get(userSelection).getSpecies());
            System.out.println("Breed: " + animals.get(userSelection).getBreed());
            System.out.println("Description: " + animals.get(userSelection).getDescription());
            MenuService.confirmDelete(userSelection);
        } else {
            System.out.printf("You haven't added enough animals! You currently have %s animals in the list.\n", animals.size());
            System.out.println("\nReturning you to the main menu so you can add more animals (or select another option...");
            int newUserSelection = MenuService.promptForMainMenuSelection(); // returns user selection
            MenuService.performRequestedAction(newUserSelection); // returns true/false to indicate if menu should display again
        }
    }
}
