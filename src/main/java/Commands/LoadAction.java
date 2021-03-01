/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import editor.Pane;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author muham
 */
public class LoadAction implements Command{
    private Pane textPane;
    JFileChooser fc;

    public LoadAction(Pane textPane) {
        this.textPane = textPane;
        this.fc = new JFileChooser();
    }

    @Override
    public boolean execute() {
           int returnValue = fc.showOpenDialog(textPane);
        if (returnValue == JFileChooser.APPROVE_OPTION) {

            try {
                Scanner s = new Scanner(fc.getSelectedFile());
                textPane.getDocument().remove(0, textPane.getDocument().getLength());
                while (s.hasNextLine()) {
                    SimpleAttributeSet defaultFont = new SimpleAttributeSet();
                    StyleConstants.setFontSize(defaultFont, 28);
                    StyleConstants.setForeground(defaultFont, Color.black);
                    
                    textPane.getDocument().insertString(textPane.getDocument().getLength()
                            , s.nextLine()+" ", defaultFont);
                  
                    
                }
            } catch (FileNotFoundException | BadLocationException ex) {
                System.out.println(ex);
                return false;
            }return true;
        } else if (returnValue == JFileChooser.CANCEL_OPTION) {
            return true;
        }
        return false;
    
    }
}
