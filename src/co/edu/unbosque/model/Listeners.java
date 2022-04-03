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

public class Listeners {
    private static final MainWindow window = MainWindow.getInstance();

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
                    JOptionPane.showMessageDialog(null, "Mala posicion", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Debes escribir texto", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

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
                    JOptionPane.showMessageDialog(null, "Mala posicion", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Debes escribir texto", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
