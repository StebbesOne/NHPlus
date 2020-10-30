package utils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ExportManager {

    /**
     * private constructor, so the class never gets instantiated
     */
    private ExportManager() {

    }

    /**
     * exports all values into a csv file
     *
     * @param toExport values to export
     */
    public static void exportToCSV(ArrayList<Object> toExport) {
        String converted = convert(toExport);
        File file = choosePath();
        if (file != null) {
            try {
                FileWriter myWriter = new FileWriter(file);
                myWriter.write(converted);
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * opens a file save dialog
     *
     * @return file that was chosen by the user
     */
    private static File choosePath() {
        JFrame frame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Suchen Sie einen Pfad zum speichern aus");
        fileChooser.setSelectedFile(new File("export.csv"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("csv file", "csv"));
        int userSelection = fileChooser.showSaveDialog(frame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }

        return null;
    }

    /**
     * puts all values of the objects to one string, separated by semicolon
     *
     * @param toConvert objects that should be converted
     * @return string, containing all values, separated by semicolon
     */
    private static String convert(ArrayList<Object> toConvert) {
        StringBuilder builder = new StringBuilder();
        for (Object obj : toConvert) {
            builder.append(obj.toString());
            builder.append(";");
        }
        String converted = builder.toString();

        // the last character gets deleted, because it is a semicolon too much
        return converted.substring(0, converted.length() - 1);
    }
}
