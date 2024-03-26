package application;

import java.util.Scanner;

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getUserInput(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine().trim();
    }

    public static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    public static MenuOption getMenuOption(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            String userInput = scanner.nextLine().trim().toLowerCase();
            MenuOption option = MenuOption.getByCode(userInput);
            if (option != null) {
                return option;
            }
            System.out.println("Invalid option. Please try again.");
        }
    }
}
