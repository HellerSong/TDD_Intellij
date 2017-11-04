package com.unittest;

import org.junit.Test;

/**
 * <p>Summary : </p>
 * <p>Authors : Heller Song (HellerSong@Outlook.com)</p>
 */
public class TestInformerStr {
    @Test
    public void test_split_informer_join_string() {
        // Means there are four informer, the last two is empty
        String str = "123111@#@1232222@#@@#@";

        String[] splitArray = str.split("@#@", -1);
        System.out.println(splitArray.length);
    }
}
