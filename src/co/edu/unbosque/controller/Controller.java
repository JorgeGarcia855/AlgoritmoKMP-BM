package co.edu.unbosque.controller;

import co.edu.unbosque.view.MainWindow;

public class Controller {
    static MainWindow window;
    public Controller() {
        window = MainWindow.getInstance();
    }
}