package com.hstdd.utils;

import org.apache.log4j.Logger;

/**
 * <p>Summary: Log system by log4j.</p>
 * <p>Authors: Heller Song (HellerSong@Outlook.com)</p>
 **/
public class DevLog {
	private static Logger logger = Logger.getLogger(DevLog.class.getName());

	public static void write(Exception e) {
		logger.error("error", e);
	}

	public static void write(String message) {
		logger.debug(message);
	}

	public static void write(int message) {
		logger.debug(message);
	}

	public static void write(boolean message) {
		logger.debug(message);
	}

	public static void main(String[] args) {
		logger.debug("Entering application.");
	}


}
