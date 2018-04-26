package iot.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;

import iot.utils.exception.NotFoundException;

public class PropertiesLoader {
	private static Properties properties;
	private static String configFile;
	
	static {
		configFile = "/config.properties";
		load();
	}
	
	public static void load() {
		try {
			properties = new Properties();
			final InputStream is = PropertiesLoader.class.getResourceAsStream(configFile);
		    properties.load(is);
		} catch (FileNotFoundException ex) {
		    ex.printStackTrace();
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
	
	public static String getCredential(String key) {
		try {
			String keyPath = properties.getProperty(key);
			byte[] keyBytes = Files.readAllBytes(new File(keyPath).toPath());
			return new String(keyBytes).trim();
		} catch(IOException e) {
			throw new NotFoundException("Credential "+key+" not found");
		}
	}
}