package co.edu.unbosque.controller;

import co.edu.unbosque.view.MainWindow;

/**
 * Esta clase inicializa la instancia de MainWindow.
 * @author Jorge García
 * @author Oscar Florez
 * @version 1.0
 */
public class Controller {
    /**
     * Constructor que solo contiene la instancia de MainWindow.
     * @author Jorge García
     * @author Oscar Florez
     */
    public Controller() {
        MainWindow.getInstance();
    }
}
