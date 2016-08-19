/**
 * Created by katherine_celeste on 8/16/16.
 */
public class Main {
    public static void main(String[] args) {

        AnimalsService service = new AnimalsService();
        MenuService menu = new MenuService();

        boolean repeatMenuOptions = true;

        do {
            int userSelection = menu.promptForMainMenuSelection(); // returns user selection

            switch (userSelection) {
                case MenuService.LIST_ANIMALS:
                    menu.displayAnimalsList(service);
                    break;

                case MenuService.CREATE_ANIMAL:
                    menu.promptForAnimalData(service);
                    break;

                case MenuService.VIEW_ANIMAL_DETAILS:
                    menu.getAnimal(service, menu);
                    break;

                case MenuService.EDIT_ANIMAL:
                    menu.editAnimal(service, menu);
                    break;

                case MenuService.DELETE_ANIMAL:
                    menu.deleteAnimal(service, menu);
                    break;

                case MenuService.EXIT_PROGRAM:
                    repeatMenuOptions = menu.confirmExit();
                    break;
            }

        } while (repeatMenuOptions);

    }
}
