/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordOperations;

import javax.swing.text.SimpleAttributeSet;

/**
 *
 * @author muham
 */
 public interface WorldOfWord {

   
    public String getText() ;
    public void setText(String str);
    public SimpleAttributeSet getFontType();
    public void setFontType(SimpleAttributeSet f);
    public Object clone() throws CloneNotSupportedException;
}
