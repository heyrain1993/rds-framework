package com.heyu.framework.utils;


import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class PropertiesUtils {

	public static String getString(String key,String fileName) {
	
		Configuration config;
		String value = "";
		try {
			config = new PropertiesConfiguration(fileName);
			value = config.getString(key, "undefined");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return value;
	}
	
}
