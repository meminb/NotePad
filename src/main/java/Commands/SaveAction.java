/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import editor.Pane;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author muham
 */
public class SaveAction implements Command{
    private Pane textPane;
  JFileChooser fc;

    public SaveAction(Pane textPane) {
        this.textPane = textPane;
        this.fc = new JFileChooser();
    }
    @Override
    public boolean execute() {fc = new JFileChooser();
        int userSelection = fc.showSaveDialog(textPane);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fc.getSelectedFile();
            try {
                FileWriter fWrite = new FileWriter(fileToSave);
                fWrite.write(textPane.getText());
                fWrite.close();
            } catch (IOException ex) {
                System.out.println("Save as file: " + fileToSave.getAbsolutePath());return false;
            }

        }
        return true;
    
    }
}
