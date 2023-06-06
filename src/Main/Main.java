package Main;

import Bank.Bank;
import MenuHandler.MainMenuHandler;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        MainMenuHandler mainMenuHandler = new MainMenuHandler();
        mainMenuHandler.displayMenu(bank);
    }
}