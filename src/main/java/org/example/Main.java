package org.example;

import org.example.Utils.DateUtils;
import org.example.View.MenuView;

public class Main {
    public static void main(String[] args) {
        MenuView menu = new MenuView();
        DateUtils utils = new DateUtils();


        // PUXAR O MENU DA CLASSE MenuView
        menu.exibirMenu();

    }
}

