/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.DocumentFilter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author muham
 */
public class Pane extends JTextPane {

    ArrayList<Integer> startingPoints;
    Dictionary dict;

    public Pane() throws CloneNotSupportedException {
        dict = new Dictionary();
        ((AbstractDocument) getDocument()).setDocumentFilter(setDocFilt());
        this.startingPoints = new ArrayList<>();
        this.startingPoints.add(0);
        getDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty, "\n");
    }

    private DocumentFilter setDocFilt() {

        return new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String str, AttributeSet attr) throws BadLocationException {

                SimpleAttributeSet defaultFont = new SimpleAttributeSet();
                StyleConstants.setFontSize(defaultFont, 28);
                StyleConstants.setForeground(defaultFont, Color.black);//doğru kelimeleri siyah yazacak

                super.replace(fb, offset, length, str, defaultFont);

//!Character.isLetterOrDigit(str.charAt(0)) && !str.equals("-") && !str.equals("\n")
                if (!Character.isLetterOrDigit(str.charAt(0)) && !str.equals("-") && !str.equals("\n")) {
                    int startOfWord = findCurrentWordIndex(offset);
                    String s = "";
                    //KELİMELERİN KONTROL EDİLMESİ
                    s = getDocument().getText(startOfWord, offset - startOfWord);
                    correctString(s, startOfWord);

                }
            }
        };

    }

    public void correctString(String str, int startOfWord) throws BadLocationException {//belirtilen kelimenin düzeltilmesi

        SimpleAttributeSet defaultFont = new SimpleAttributeSet();
        StyleConstants.setFontSize(defaultFont, 28);
        StyleConstants.setForeground(defaultFont, Color.black);//doğru kelimeleri siyah yazacak

        SimpleAttributeSet wrongWordAttr = new SimpleAttributeSet();
        StyleConstants.setForeground(wrongWordAttr, Color.RED);//yanlışları kırmızı
        StyleConstants.setFontSize(wrongWordAttr, 28);

        SimpleAttributeSet editedFont = new SimpleAttributeSet();
        StyleConstants.setFontSize(editedFont, 28);
        StyleConstants.setForeground(editedFont, Color.GRAY);//düzeltilenleri gri

        String trueStr = str.replace(" ", "");
        boolean isDigit = false;
        try {
            Integer.parseInt(trueStr);
            isDigit = true;
        } catch (Exception e) {
        }
        getDocument().remove(startOfWord, trueStr.length());
        if (trueStr.contains("-")) {//aşağıdaki else if kısmının - içerenler 
            int t = trueStr.indexOf('-');

            String strWithoutMines = trueStr.replace("-", "");

            if (!dict.isWordExistInRightWords(strWithoutMines)) {

                String stW = dict.isWordExistInSingleTranspositions(strWithoutMines);
                String stWord = stW.substring(0, t) + "-" + stW.substring(t, stW.length());
                System.out.println(stWord);
                if (strWithoutMines.equals(stWord)) {
                    getDocument().insertString(startOfWord, stWord, wrongWordAttr);
                } else {
                    getDocument().insertString(startOfWord, stWord, editedFont);
                }
            } else {
                getDocument().insertString(startOfWord, trueStr, defaultFont);
            }

        } else if (!isDigit) {

            System.out.println(trueStr + ".");
            if (!dict.isWordExistInRightWords(trueStr)) {// kelime yanlış mı
                String stWord
                        = dict.isWordExistInSingleTranspositions(trueStr);
                if (trueStr.equals(stWord)) {                                   //Kelime Düzeltilmeyecek is kırmızı yapılır
                    getDocument().insertString(startOfWord, stWord, wrongWordAttr);
                    System.out.println("hayda");
                } else {                                                      //düzeltilecekse düzeltilir ve yeşil yapılır
                    getDocument().insertString(startOfWord, stWord, editedFont);
                }
            } else {//kelime doru ise geri eklenir
                //doğru kelimeler hiç çıkartılmadan devam ettirilebilirdi amabu durumda 
                //yanlış kelimenin yanlış olan kısmı silindiğinde kelimenin kalan kısmı doğru kelime
                //rengine dönmez
                getDocument().insertString(startOfWord, trueStr, defaultFont);
            }

        }

    }

    /*
   Bütün metnin içerisndeki kelimelerin başlangıç 
    noktalarının tekrar indexlenmesi için kullanılır.Bu metod bütün metnin
    ortalarında bir değişiklik yapıldığında gerekli olur. 
     */
    public void reAssignStartOfWords() {//kelime başlangıç nktalarının tekrar atanması
        String text = getText();
        startingPoints.clear();
        startingPoints.add(0);
        // boolean isSpacesEnd=false;
        for (int i = 1; i < text.length(); i++) {
            if (Character.isLetterOrDigit(text.charAt(i))
                    && (!Character.isLetterOrDigit(text.charAt(i - 1))
                    && text.charAt(i - 1) != '-')) {
                startingPoints.add(i);
            }
        }
    }

    /*
   Değiştirilmekte olan kelimenin metindeki 
    başladığı yeri bulmak için  kullanılır. 
     */
    public int findCurrentWordIndex(int cursorIndex) {
        reAssignStartOfWords();
        int index = 0;
        for (Integer startingPoint : startingPoints) {
            if (cursorIndex < startingPoint) {
                break;
            }
            index = startingPoint;
        }
        return index;

    }/*
   metodu textPane içerisindeki bütün kelimeler için correctString() methodunu çağırır .
    Yapıştırılan metinler ve açılan dosyalar için kullanılır 
     */
  
}
