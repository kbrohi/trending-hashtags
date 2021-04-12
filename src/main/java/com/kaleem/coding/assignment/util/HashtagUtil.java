package com.kaleem.coding.assignment.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HashtagUtil {
	
	private static Pattern MY_PATTERN = null;

	public static List<String> getTags(String tweet, String regEx) {
		List<String> tags = new ArrayList<>();
		Matcher mat = getPattern(regEx).matcher(tweet);
		while (mat.find()) {
			tags.add(mat.group());
		}
		return tags;
	}

	public static Pattern getPattern(String regEx) {
		if (MY_PATTERN == null) {
			MY_PATTERN = Pattern.compile(regEx);
		}
		return MY_PATTERN;
	}

}
