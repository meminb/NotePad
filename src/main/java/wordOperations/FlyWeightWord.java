/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordOperations;

import java.util.HashMap;

/**
 *
 * @author muham
 */
public class FlyWeightWord {

    private static final HashMap<Character, CorrectWord> corHash = new HashMap();

    public static CorrectWord getWord(String word) {

        CorrectWord tempWord = (CorrectWord) corHash.get(word.charAt(0));

        if (tempWord == null) {
            tempWord = new CorrectWord(word);
            corHash.put(word.charAt(0), tempWord);
        }
        return tempWord;

    }

    public static HashMap<Character, CorrectWord> getCorHash() {
        return corHash;
    }

   

}
