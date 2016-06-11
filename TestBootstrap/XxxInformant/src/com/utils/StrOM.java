package com.utils;

import java.security.InvalidParameterException;
import java.util.Locale;

/**
 * <p>Summary: String operation management.</p>
 * <p>Authors: Heller Song (HellerSong@Outlook.com)</p>
 **/
public class StrOM {
    private StrOM() {
    }

    public static String upperWordFirstLetter(String word) {
        if (word == null || word.length() <= 0)
            throw new InvalidParameterException();

        String firstChar = word.substring(0, 1);
        word = word.replaceFirst(firstChar, firstChar.toUpperCase(Locale.ENGLISH));

        return word;
    }

    public static String upperWordAllLetters(String word) {
        if (word == null || word.length() <= 0)
            throw new InvalidParameterException();

        return word.toUpperCase(Locale.ENGLISH);
    }

    public static String upperSentenceToTitleCase(String sentence) {
        if (sentence == null || sentence.length() <= 0)
            throw new InvalidParameterException();

        String temp = "";
        String[] wordArray = DevRegex.getMatchedStringArray(DevRegex.MATCH_WORD, sentence);
        for (String s : wordArray) {
            temp += s + " ";
        }

        return temp.substring(0, temp.length() - 1);
    }

    public static String upperSentenceFirstLetter(String sentence) {
        if (sentence == null || sentence.length() <= 0)
            throw new InvalidParameterException();

        return upperWordFirstLetter(sentence);
    }

    /**
     * Convert "FirstName" to "firstName"
     */
    public static String convertToLowerCamelCase(String originalStr) {
        if (originalStr == null || originalStr.length() <= 0)
            throw new InvalidParameterException();

        String result = "";
        String[] words = DevRegex.getMatchedStringArray(DevRegex.MATCH_WORD, originalStr);

        // The first word in a special treatment, lower, others upper
        result += words[0].toLowerCase(Locale.ENGLISH);
        for (int i = 1; i < words.length; i++) {
            // Left words all to upper
            result += upperWordFirstLetter(words[i]);
        }

        return result;
    }

    /**
     * Convert "firstName" to "FirstName"
     */
    public static String convertToUpperCamelCase(String originalStr) {
        if (originalStr == null || originalStr.length() <= 0)
            throw new InvalidParameterException();

        String result = "";
        String[] words = DevRegex.getMatchedStringArray(DevRegex.MATCH_WORD, originalStr);

        // Upper all words
        for (String w : words)
            result += upperWordFirstLetter(w);

        return result;
    }

    /**
     * Convert "firstName" to "FIRST_NAME"
     */
    public static String convertToUnixCase(String originalStr) {
        if (originalStr == null || originalStr.length() <= 0)
            throw new InvalidParameterException();

        String result = "";
        String[] words = DevRegex.getMatchedStringArray(DevRegex.MATCH_WORD, originalStr);

        // Append "_" between each lower case word
        for (String w : words) {
            result += w.toLowerCase(Locale.ENGLISH) + "_";
        }
        // Remove the tail char "_"
        result = result.substring(0, result.length() - 1);

        return result;
    }
}
