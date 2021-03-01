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
import javax.swing.JOptionPane;

/**
 *
 * @author muham
 */
public class SaveAsAction implements Command{
    private Pane textPane;

    public SaveAsAction(Pane textPane) {
        this.textPane = textPane;
    }
    
    

    @Override
    public boolean execute() {
       String s = JOptionPane.showInputDialog("Dosyanın adını ,\n (isterseniz oluşturulacağı  yolla birlikte)\n girin");

        // System.out.println(s);
        String stPath = s + ".txt";
        try {
            File myObj = new File(stPath);
            if (myObj.createNewFile()) {
                FileWriter fWrite = new FileWriter(myObj);
                fWrite.write(textPane.getText());
                fWrite.close();

                // System.out.println("File created: " + myObj.getName());
            } else {
                JOptionPane.showMessageDialog(textPane, "Dosya zaten mevcut");
            }
            return true;
        } catch (IOException a) {
            JOptionPane.showMessageDialog(textPane, "Dosya yolu bulunamadı");
            return false;
        }
    
    
    }
    
    
    
}
