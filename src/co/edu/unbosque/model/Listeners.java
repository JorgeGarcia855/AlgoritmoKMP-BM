package co.edu.unbosque.model;

import co.edu.unbosque.view.MainWindow;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Listeners {
    public static void loadFileListener() {
        MainWindow window = MainWindow.getInstance();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Archivo de Texto", "txt"));
        fileChooser.showOpenDialog(window);

        String filePath = String.valueOf(fileChooser.getSelectedFile());
        StringBuilder textLines = new StringBuilder();
        try (Stream<String> lines = (Files.newBufferedReader(Path.of(filePath)).lines())) {
            lines.forEach(s -> textLines.append(s).append("\n"));
            window.getTextArea().setText(textLines.toString());
        } catch (IOException ex) {
            new JOptionPane(JOptionPane.ERROR_MESSAGE).setMessage("Error al abrir el archivo");
        }
    }

    public static void KMPListener() {
        System.out.println("kmp is listening");
    }

    public static void BMListener() {
        System.out.println("bm is listening");
    }
}
