package com.heyu.framework.utils;

public class StringUtils {

	public static String underlineToHump(String param) {
		if(isEmpty(param)) {
			return "";
		}
		StringBuffer buffer = new StringBuffer();
		String[] strs = param.split("_");
		for (String str:strs) {
			if(buffer.length() == 0) {
				buffer = buffer.append(str.toLowerCase());
			}else {
				buffer.append(str.substring(0, 1).toUpperCase());
				buffer.append(str.substring(1).toLowerCase());
			}
		}
		return buffer.toString();
	}
	
	public static boolean isEmpty(String param) {
		if(param != null && !"".equals(param)) {
			return false;
		}
		return true;
	}
	
	public static String firstToUpper(String param) {
		if(isEmpty(param)) {
			return "";
		}
		char[] chars = param.toCharArray();  
	    if (chars[0] >= 'a' && chars[0] <= 'z') {  
	    	chars[0] = (char) (chars[0] - 32);  
	    }  
	    return new String(chars); 
	}
}
