/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import editor.Pane;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;

/**
 *
 * @author muham
 */
public class Button extends JMenuItem{
    
    
    public Button(String text,Command comm) {
        this.setText(text);
        setAction(comm);
    }

    
    
    
    public final void  setAction(Command comm){
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comm.execute();
            }
        });
    }
    
      
    
}


