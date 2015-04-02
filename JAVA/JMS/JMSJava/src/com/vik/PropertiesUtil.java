package com.vik;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

	/**
	 * @param args
	 */

	private PropertiesUtil() {
		// TODO Auto-generated constructor stub
	}

	public static Properties prop = new Properties();

	static {

		try {

			String filename = "jndi.properties";
			InputStream input = PropertiesUtil.class.getClassLoader().getResourceAsStream(filename);
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
			}
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public static String getProperty(String key) {

		return (prop.getProperty(key)==null?"":prop.getProperty(key));
	}

	public static void main(String[] args) {

		System.out.println("java.naming.factory.initial\t\t: " + prop.getProperty("java.naming.factory.initial"));
		System.out.println("java.naming.provider.url\t\t: " + prop.getProperty("java.naming.provider.url"));
		System.out.println("java.naming.security.principal\t\t: " + prop.getProperty("java.naming.security.principal"));
		System.out.println("java.naming.security.credentials\t: " + prop.getProperty("java.naming.security.credentials"));

	}
}