package co.edu.unbosque.view;

import co.edu.unbosque.model.Listeners;

import javax.swing.*;
import java.awt.*;

/**
 * Esta clase contiene todos los componentes de la
 * interfaz gráfica.
 * @author Jorge García
 * @author Oscar Florez
 * @version 1.0
 */
public class MainWindow extends JFrame {

    /**
     * Atributo que crea una instancia nula de
     * esta clase.
     */
    private static MainWindow mainWindow = null;
    /**
     * Atributo que genera un arreglo de botones.
     */
    private final JButton[] button = new JButton[4];
    /**
     * Atributo que genera un área de texto, para poder
     * mostrar el archivo de texto.
     */
    private final JTextArea textArea = new JTextArea();
    /**
     * Atributo que genera un campo de texto. Permite
     * ingresar el patron de texto a buscar.
     */
    private final JTextField textField = new JTextField();

    /**
     * Atributo que genera una línea de texto mostrando
     * las veces que se encontró el patron con el algoritmo escogido.
     */
    private final JLabel noOfTimes = new JLabel();

    /**
     * Un constructor privado de MainWindow. Impide que
     * se pueda crear otras instancias de la clase.
     * @author Jorge García
     * @author Oscar Florez
     */
    private MainWindow() {
        setTitle("Programa Algoritmos KMP y BM");
        setSize(700, 500);
        getContentPane().setLayout(null);
        initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Inicializa cada componente de la interfaz gráfica.
     * Los botones usan lambdas para poder ejecutar los
     * ActionEvent.
     * @author Jorge García
     * @author Oscar Florez
     */
    private void initComponents() {
        textArea.setEditable(false);
        JScrollPane scrollArea = new JScrollPane(textArea);
        scrollArea.setBounds(10, 10, 350, 390);
        scrollArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        getContentPane().add(scrollArea);

        JLabel enterText = new JLabel("Ingrese texto a buscar");
        enterText.setBounds(10, 410, 350, 20);
        getContentPane().add(enterText);

        textField.setBounds(10, 430, 350, 20);
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        getContentPane().add(textField);

        button[0] = new JButton("Abrir Archivo");
        button[0].setBounds(375, 10, 300, 40);
        button[0].addActionListener(evt -> Listeners.loadFileListener());
        getContentPane().add(button[0]);

        button[1] = new JButton("Buscar con Algoritmo KMP");
        button[1].setBounds(375, 70, 300, 40);
        button[1].addActionListener(evt -> Listeners.KMPListener());
        getContentPane().add(button[1]);

        button[2] = new JButton("Buscar con Algoritmo BM");
        button[2].setBounds(375, 130, 300, 40);
        button[2].addActionListener(evt -> Listeners.BMListener());
        getContentPane().add(button[2]);

        button[3] = new JButton("Salir");
        button[3].setBounds(375, 410, 300, 40);
        button[3].addActionListener(evt -> System.exit(0));
        getContentPane().add(button[3]);

        noOfTimes.setBounds(375, 200, 450, 20);
        getContentPane().add(noOfTimes);
    }

    /**
     * Genera una única instancia de la clase MainWindow.
     * @return la instancia de esta clase.
     * @author Jorge García
     * @author Oscar Florez
     */
    public static MainWindow getInstance() {
        if (mainWindow == null) mainWindow = new MainWindow();
        return mainWindow;
    }

    /**
     * Permite acceder al área de texto en
     * otras clases.
     * @return el atributo textArea.
     * @author Jorge García
     * @author Oscar Florez
     */
    public JTextArea getTextArea() {
        return textArea;
    }

    /**
     * Permite acceder al campo de texto en
     * otras clases.
     * @return el atributo textField.
     * @author Jorge García
     * @author Oscar Florez
     */
    public JTextField getTextField() {
        return textField;
    }

    /**
     * Permite acceder a la línea de texto
     * en otras clases.
     * @return el atributo noOfTimes.
     * @author Jorge García
     * @author Oscar Florez
     */
    public JLabel getNoOfTimes() {
        return noOfTimes;
    }
}
