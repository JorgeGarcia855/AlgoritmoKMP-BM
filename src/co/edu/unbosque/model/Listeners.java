package co.edu.unbosque.model;

import co.edu.unbosque.view.MainWindow;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Esta clase contiene todos los eventos a ser
 * escuchados por el ActionEvent de los botones.
 * @author Jorge García
 * @author Oscar Florez
 * @version 1.0
 */
public class Listeners {
    /**
     * Este atributo contiene la instancia de MainWindow.
     */
    private static final MainWindow window = MainWindow.getInstance();

    /**
     * Permite entrar a la ventana de selección de archivos que muestra
     * únicamente archivos de texto plano (.txt). También carga el archivo
     * al JTextArea.
     * @author Jorge García
     * @author Oscar Florez
     */
    public static void loadFileListener() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Archivo de Texto", "txt"));
        fileChooser.showOpenDialog(window);

        String filePath = String.valueOf(fileChooser.getSelectedFile());
        StringBuilder textLines = new StringBuilder();
        try (Stream<String> lines = Files.newBufferedReader(Path.of(filePath)).lines()) {
            lines.forEach(s -> textLines.append(s).append("\n"));
            window.getTextArea().setText(textLines.toString());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Resalta todos los patrones de texto con color amarillo,
     * usando el algoritmo KMP.
     * @author Jorge García
     * @author Oscar Florez
     */
    public static void KMPListener() {
        try {
            Algorithms.getIndexesKMP().clear();
            window.getTextArea().getHighlighter().removeAllHighlights();
            int patLen = window.getTextField().getText().length();
            Algorithms.KMP(window.getTextField().getText(), window.getTextArea().getText());
            Algorithms.getIndexesKMP().forEach(i -> {
                try {
                    window.getTextArea()
                            .getHighlighter()
                            .addHighlight(i, patLen + i, new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW));
                } catch (BadLocationException e) {
                    JOptionPane.showMessageDialog(null, "Mala posición", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            window.getNoOfTimes().setText("# de veces encontrado con KMP: "+ Algorithms.getIndexesKMP().size());
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Debes escribir texto", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Resalta todos los patrones de texto con color cian,
     * usando el algoritmo BM.
     * @author Jorge García
     * @author Oscar Florez
     */
    public static void BMListener() {
        try {
            Algorithms.getIndexesBM().clear();
            window.getTextArea().getHighlighter().removeAllHighlights();
            int patLen = window.getTextField().getText().length();
            Algorithms.BM(window.getTextArea().getText().toCharArray(), window.getTextField().getText().toCharArray());
            Algorithms.getIndexesBM().forEach(i -> {
                try {
                    window.getTextArea()
                            .getHighlighter()
                            .addHighlight(i, patLen + i, new DefaultHighlighter.DefaultHighlightPainter(Color.CYAN));
                } catch (BadLocationException e) {
                    JOptionPane.showMessageDialog(null, "Mala posición", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            window.getNoOfTimes().setText("# de veces encontrado con BM: "+ Algorithms.getIndexesBM().size());
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Debes escribir texto", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
