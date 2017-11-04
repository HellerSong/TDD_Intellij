package com.hstdd.utils;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * <p>Summary: Filter all by regular expression.</p>
 * <p>Authors: Heller Song (HellerSong@Outlook.com)</p>
 **/
public class DevRegex {
	// #//Match value, used for getting matched sub string.
	public static final String MATCH_INTEGER = "-{0,1}\\d+";
	public static final String MATCH_4INTEGER = "\\d{4}";
	public static final String MATCH_POSITIVE_INTEGER = "\\d+";
	public static final String MATCH_NEGATIVE_INTEGER = "-\\d+";
	public static final String MATCH_WORD = "[A-Za-z]([a-z]+|[A-Z]+)";
	public static final String MATCH_CNWORD = "[\\u2E80-\\u9FFF]+";

	// #//Check value, used for validating full string is what format you want.
	public static final String CHECK_PASSWORD = "^[a-z0-9_-]{6,18}$";
	public static final String CHECK_INTEGER = "^-{0,1}\\d+$";
	public static final String CHECK_IPV4 = "^(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]){3}$";
	public static final String CHECK_PHONE_NUMBER = "^\\+?[\\d\\s]{3,}$";
	public static final String CHECK_EMAIL = "^[\\_]*([a-z0-9]+(\\.|\\_*)?)+@([a-z][a-z0-9\\-]+(\\.|\\-*\\.))+[a-z]{2,6}$";
	public static final String CHECK_START_WITH_DOES = "^(D|d)oes\\w*";

	private DevRegex() {
	}

	public static Boolean checkIsMatch(String checkPattern, String input) {
		if (checkPattern == null || checkPattern.length() <= 0)
			throw new InvalidParameterException();
		if (input == null || input.length() <= 0)
			throw new InvalidParameterException();

		return Pattern.matches(checkPattern, input);
	}

	public static String getMatchedString(String matchPattern, String input) {
		if (matchPattern == null || matchPattern.length() <= 0)
			throw new InvalidParameterException();
		if (input == null || input.length() <= 0)
			throw new InvalidParameterException();

		Matcher matcher = Pattern.compile(matchPattern).matcher(input);
		matcher.find();

		return matcher.group(0);
	}

	public static String[] getMatchedStringArray(String matchPattern, String input) {
		if (matchPattern == null || matchPattern.length() <= 0)
			throw new InvalidParameterException();
		if (input == null || input.length() <= 0)
			throw new InvalidParameterException();

		List<String> list = new ArrayList<>();
		Matcher matcher = Pattern.compile(matchPattern).matcher(input);
		while (matcher.find()) {
			list.add(matcher.group(0));
		}

		return list.toArray(new String[list.size()]);
	}

}
