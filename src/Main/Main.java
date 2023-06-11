package Main;

import Bank.Bank;
import MenuHandler.Handler.MainMenuHandler;


// there is GUIDELINE.txt to make it easy to use
// you can check it if you have a problem

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        MainMenuHandler mainMenuHandler = new MainMenuHandler();
        mainMenuHandler.displayMenu(bank);
    }
}