package com.api.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	// Read the properties file

	private ConfigManager() {
		// private constructor
	}

	private static Properties prop = new Properties();
	private static String env;
	private static String path = "config/config.properties";

	static {
//		File configFile = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
//				+ File.separator + "resources" + File.separator + "config" + File.separator + "config.properties");
//		FileReader fileReader;

		env = System.getProperty("env", "qa");
		env = env.toLowerCase().trim();

		switch (env) {
		case "dev" -> path = "config/config_dev.properties";

		case "qa" -> path = "config/config_qa.properties";

		case "uat" -> path = "config/config_uat.properties";

		default -> path = "config/config_qa.properties";
		}

		InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

		try {
			// fileReader = new FileReader(configFile);
			prop.load(stream);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static String getProperty(String key) throws IOException {

		return prop.getProperty(key);
	}

}
