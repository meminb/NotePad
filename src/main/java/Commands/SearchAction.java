/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import editor.Pane;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author muham
 */
public class SearchAction implements Command {

    private Pane textPane;

    public SearchAction(Pane textPane) {
        this.textPane = textPane;
    }

    @Override
    public boolean execute() {
        String str = JOptionPane.showInputDialog("Aranacak kelime") + "";

        if (textPane.getText().contains(str)) {

            int i = textPane.getText().indexOf(str);

            DefaultHighlighter.DefaultHighlightPainter highlightPainter
                    = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);

            try {

                textPane.getHighlighter().addHighlight(i, i + str.length(), highlightPainter);

                SimpleAttributeSet fontType = new SimpleAttributeSet();
                StyleConstants.setFontSize(fontType, 28);
                StyleConstants.setForeground(fontType, Color.black);

                String strChange = JOptionPane.showInputDialog("Şununla değiştir:") + "";
                textPane.getDocument().remove(i, str.length());
                textPane.getDocument().insertString(i, strChange, fontType);

            } catch (BadLocationException ex) {
                System.out.println(ex);
                return false;
            }
        }

        return true;
    }

}
