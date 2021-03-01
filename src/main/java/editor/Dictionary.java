/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import wordOperations.CorrectWord;
import wordOperations.FlyWeightWord;
import wordOperations.SingleTransWord;
import wordOperations.WorldOfWord;

/**
 *
 * @author muham
 */
public class Dictionary {

    
    ArrayList<WorldOfWord> rightDict;
    ArrayList<WorldOfWord> stDict;

    public Dictionary() throws CloneNotSupportedException {
        this.rightDict = new ArrayList<>();
        this.stDict = new ArrayList<>();
        try {//dosya okuma ve ekleme işlemleri..
           
            File f = new File("words.txt");
            Scanner reader = new Scanner(f);
            while (reader.hasNextLine()) {
                
                String str = reader.nextLine();
                CorrectWord temp=(CorrectWord)FlyWeightWord.getWord(str).clone();//flyWeightDesign pattern
                 temp.setText(str);
                rightDict.add(temp);
                stDict.addAll(findTranspositions(str, 0));

            }

        } catch (FileNotFoundException ex) {System.out.println("File not found");
        }

    }
    public boolean isWordExistInRightWords(String word) {
        Iterator it = rightDict.iterator();
        while (it.hasNext()) {
            CorrectWord w = (CorrectWord) it.next();
            if (w.getText().equals(word)) {
                return true;
            } 
        }
        return false;
    }
    
    
 public String isWordExistInSingleTranspositions(String word) {
        Iterator it = stDict.iterator();
        while (it.hasNext()) {
            WorldOfWord w = (WorldOfWord) it.next();
            if (w.getText().equals(word)) {
                return ((SingleTransWord)w).getCorrectWord();
            } 
        }
       

        return word;
    }
    final ArrayList<SingleTransWord> findTranspositions(String s, int index) {

        ArrayList<SingleTransWord> arrOfAll = new ArrayList<>();

        if (s.length() > index + 1) {
            arrOfAll.addAll(findTranspositions(s, index + 1));
        }
        char[] arrStr = s.toCharArray();
        char baseChar = s.charAt(index);
        for (int i = index + 1; i < s.length(); i++) {
            char[] tempOfStr = arrStr.clone();
            tempOfStr[index] = tempOfStr[i];
            tempOfStr[i] = baseChar;
            arrOfAll.add(new SingleTransWord(s, String.valueOf(tempOfStr)));
        }
        return arrOfAll;
    }

}
