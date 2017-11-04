package com.utils;

import java.security.InvalidParameterException;
import java.util.UUID;

/**
 * <p>Summary: </p>
 * <p>Authors: Heller Song (HellerSong@Outlook.com)</p>
 **/
public class DevRandom {
    public static String GetRandomCodeNoRepeat(int codeCount) {
        if (codeCount <= 0)
            throw new InvalidParameterException();

        String allChars = "1,2,3,4,5,6,7,8,9,A,B,C,D,E,F,G,H,i,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
        String[] allCharArray = allChars.split(",");
        String randomCode = "";
        int temp = -1;
        int rand = (int) ((Math.random() * 100) % allCharArray.length);

        for (int i = 0; i < codeCount; i++) {
            if (temp != -1) {
                rand = (int) ((Math.random() * 100) % allCharArray.length);
            }

            int t = (int) (Math.random() * allCharArray.length);

            while (temp == t) {
                t = (int) (Math.random() * allCharArray.length);
            }

            temp = t;
            randomCode += allCharArray[rand];
        }

        return randomCode;
    }

    public static String createRandomString(int length) {
        if (length <= 0)
            throw new InvalidParameterException();

        String randomStr = "";
        String uuid = UUID.randomUUID().toString().replace("-", "");

        for (int i = 0; i < length; i++) {
            double numb = Math.random() * uuid.length();
            randomStr += uuid.charAt((int) numb);
        }

        return randomStr.toUpperCase();
    }

}
