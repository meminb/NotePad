/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordOperations;

import java.awt.Color;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author muham
 */
public class CorrectWord implements WorldOfWord,Cloneable {

    protected SimpleAttributeSet fontType;
    protected String word;

    public CorrectWord(String str) {
        fontType = new SimpleAttributeSet();
        StyleConstants.setFontSize(this.fontType, 28);
        StyleConstants.setForeground(this.fontType, Color.black);
        this.word = str;
    }

    @Override
    public String getText() {
        return word;
    }

    @Override
    public SimpleAttributeSet getFontType() {
        return fontType;
    }
  @Override
    public void setText(String str) {
       this.word=str; }

    @Override
    public void setFontType(SimpleAttributeSet f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
}
