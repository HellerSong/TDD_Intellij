package com.hstdd.test.utils;

import com.hstdd.utils.DevRegex;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Summary: </p>
 * <p>Authors: Heller Song (HellerSong@Outlook.com)</p>
 **/
public class TestDevRegex {
	@Test
	public void test_match_words_among_sentence()
	{
		String line = "This. is a /string--test ADD_USER";
		Matcher m = Pattern.compile(DevRegex.MATCH_WORD).matcher(line);
		while (m.find()) {
			System.out.println(m.group(0));
		}
	}
}
