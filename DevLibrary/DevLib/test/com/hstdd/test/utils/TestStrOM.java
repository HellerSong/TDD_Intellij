package com.hstdd.test.utils;

import com.hstdd.utils.StrOM;
import org.junit.Test;

/**
 * <p>Summary: </p>
 * <p>Authors: Heller Song (HellerSong@Outlook.com)</p>
 **/
public class TestStrOM {

	@Test
	public void test_convert_word_upper_to_title() {
		// All will convert like this: "FirstName", "UserGroup"
		System.out.println(StrOM.convertToUpperCamelCase("firstName"));
		System.out.println(StrOM.convertToUpperCamelCase("user_group"));
		System.out.println(StrOM.convertToUpperCamelCase("control_category_id"));
		System.out.println(StrOM.convertToUpperCamelCase("ADD_USER"));
		System.out.println();
	}

	@Test
	public void test_convert_to_lower_camel_case() {
		// All will convert like this: "firstName", "controlCategoryId"
		System.out.println(StrOM.convertToLowerCamelCase("FirstName"));
		System.out.println(StrOM.convertToLowerCamelCase("ControlCategoryId"));
		System.out.println(StrOM.convertToLowerCamelCase("user_group"));
		System.out.println(StrOM.convertToLowerCamelCase("control_category_id"));
		System.out.println(StrOM.convertToLowerCamelCase("ADD_USER"));
		System.out.println();
	}

	@Test
	public void test_convert_to_unix_system_case() {
		// All will convert like this: "first_name", "control_category_id"
		System.out.println(StrOM.convertToUnixCase("FirstName"));
		System.out.println(StrOM.convertToUnixCase("controlCategoryId"));
		System.out.println(StrOM.convertToUnixCase("ADD_USER"));
		System.out.println();
	}
}
