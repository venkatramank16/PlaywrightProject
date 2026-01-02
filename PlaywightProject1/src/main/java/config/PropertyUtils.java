package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertyUtils {
	private Logger log = LogManager.getLogger();
	private Properties properties;

	public PropertyUtils() {
		this.properties = new Properties();
		try (FileInputStream fileInputStream = new FileInputStream("./src/main/resources/config.properties")) {
			properties.load(fileInputStream);
		} catch (IOException e) {
			log.error("Error while reading properties file ", e);
		}
	}

	public String getProperty(String key) {
		return properties.getProperty(key) != null ? properties.getProperty(key).trim() : null;
	}

}
