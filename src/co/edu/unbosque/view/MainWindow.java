package co.edu.unbosque.view;

import co.edu.unbosque.model.Listeners;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private static MainWindow mainWindow = null;
    private final JButton[] button = new JButton[4];
    private final JTextArea textArea = new JTextArea();
    private final JTextField textField = new JTextField();

    private MainWindow() {
        setTitle("Programa Algoritmos KMP y BM");
        setSize(600, 500);
        getContentPane().setLayout(null);
        initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        textArea.setEditable(false);
        JScrollPane scrollArea = new JScrollPane(textArea);
        scrollArea.setBounds(10, 10, 350, 390);
        scrollArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        getContentPane().add(scrollArea);

        JLabel label = new JLabel("Ingrese texto a buscar");
        label.setBounds(10, 410, 350, 20);
        getContentPane().add(label);

        textField.setBounds(10, 430, 350, 20);
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        getContentPane().add(textField);

        button[0] = new JButton("Abrir Archivo");
        button[0].setBounds(375, 10, 200, 40);
        button[0].addActionListener(evt -> Listeners.loadFileListener());
        getContentPane().add(button[0]);

        button[1] = new JButton("Buscar con Algoritmo KMP");
        button[1].setBounds(375, 70, 200, 40);
        button[1].addActionListener(evt -> Listeners.KMPListener());
        getContentPane().add(button[1]);

        button[2] = new JButton("Buscar con Algoritmo BM");
        button[2].setBounds(375, 130, 200, 40);
        button[2].addActionListener(evt -> Listeners.BMListener());
        getContentPane().add(button[2]);

        button[3] = new JButton("Salir");
        button[3].setBounds(375, 410, 200, 40);
        button[3].addActionListener(evt -> System.exit(0));
        getContentPane().add(button[3]);
    }

    public static MainWindow getInstance() {
        if (mainWindow == null) mainWindow = new MainWindow();
        return mainWindow;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JTextField getTextField() {
        return textField;
    }
}
