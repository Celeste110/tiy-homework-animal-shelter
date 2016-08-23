import repository.AnimalRepository;
import service.AnimalsService;
import service.MenuService;

import java.io.IOException;

/**
 * Created by katherine_celeste on 8/16/16.
 */
public class Main {
    public static void main(String[] args) {

        AnimalRepository animalRepository = null;
        try {
            animalRepository = new AnimalRepository("animals.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        MenuService menu = new MenuService(new AnimalsService(animalRepository));

        boolean repeatMenuOptions = true;

        do {
            int userSelection = menu.promptForMainMenuSelection(); // returns user selection

            if (userSelection >= 1 && userSelection <= 6)  // check to see if a valid option was entered
            {
                switch (userSelection) {
                    case MenuService.LIST_ANIMALS:
                        menu.displayAnimalsList();
                        break;

                    case MenuService.CREATE_ANIMAL:
                        menu.promptForAnimalData();
                        break;

                    case MenuService.VIEW_ANIMAL_DETAILS:
                        menu.getAnimal();
                        break;

                    case MenuService.EDIT_ANIMAL:
                        menu.editAnimal();
                        break;

                    case MenuService.DELETE_ANIMAL:
                        menu.deleteAnimal();
                        break;

                    case MenuService.EXIT_PROGRAM:
                        repeatMenuOptions = menu.confirmExit();
                        break;
                }

            } else {
                System.out.println("Error: Sorry, that isn't a valid option.");
            }

        } while (repeatMenuOptions);
    }
}
