package com.matches;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testmatch {

	public static void main(String[] args) {
		String string = "ajaxDsfsfa";
		 Pattern p = Pattern.compile("^ajax");
		 Matcher matcher = p.matcher(string);
		 if (matcher.matches()) {
			 System.out.println("su");
		}
		boolean is = string.matches("[\\w]");
		System.out.println(is);
	}

}
