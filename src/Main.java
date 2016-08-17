/**
 * Created by katherine_celeste on 8/16/16.
 */
public class Main {
    public static void main(String[] args) {
        boolean repeatMenuOptions;
        do {
            int userSelection = MenuService.promptForMainMenuSelection(); // returns user selection
            repeatMenuOptions = MenuService.performRequestedAction(userSelection); // returns true/false to indicate if menu should display again
        } while (repeatMenuOptions);

    }
}
