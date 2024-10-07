package xyz.elspeth.config;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import xyz.elspeth.Main;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

public class ConfigManager {
	
	private static final String CONFIG_FILE_NAME = "config.json";

	private static Path getConfigDirectory() {
		
		try {
			var path = Path.of(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent().resolve("config");
			if (!path.toFile().exists()) {
				if(!path.toFile().mkdirs()) {
					throw new RuntimeException("Failed to create config directory");
				}
			}
			return path;
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static Path getConfigFile() {
		return getConfigDirectory().resolve(CONFIG_FILE_NAME);
	}
	
	private static Config saveDefaultConfig() {
		
		var defaultConfig = new Config();
		defaultConfig.VERSION = Config.CURRENT_VERSION;
		
		var config = saveConfig(defaultConfig);
		System.out.println("Saved default config to " + getConfigFile().toFile().getAbsolutePath());
		return config;
	}
	
	private static Config saveConfig(Config config) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		
		var path = getConfigFile();
		
		if (path.toFile().exists()) {
			path.toFile().renameTo(path.getParent().resolve(CONFIG_FILE_NAME + ".bak").toFile());
		}
		
		try {
			objectMapper.writeValue(getConfigFile().toFile(), config);
			return config;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Config getConfig() {
	
		var file = getConfigFile();
		
		if (!file.toFile().exists()) {
			saveDefaultConfig();
			System.exit(1);
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		Config config = null;
		boolean recreate = false;
		
		try {
			config = objectMapper.readValue(file.toFile(), Config.class);
		} catch (DatabindException ignored) {
			recreate = true;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		if (config == null || !Config.CURRENT_VERSION.isEqualTo(config.VERSION)) {
			recreate = true;
		}
		
		if (recreate) {
			recreate = false;
			try {
				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				config = objectMapper.readValue(file.toFile(), Config.class);
				config.VERSION = Config.CURRENT_VERSION;
				saveConfig(config);
			} catch (IOException e) {
				recreate = true;
			}
		}
		
		if (recreate) {
			System.out.println("Corrupted or old config. Recreating new config.");
			config = saveDefaultConfig();
		}
		
		return config;
	}
	
}
