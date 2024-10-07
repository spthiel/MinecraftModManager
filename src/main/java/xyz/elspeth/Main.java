package xyz.elspeth;

import xyz.elspeth.config.ConfigManager;

import java.net.URISyntaxException;
import java.nio.file.Path;

public class Main {
	
	public static void main(String[] args) {
		
		
		System.out.println(ConfigManager.getConfig().modrinthAPIKey);
	}
}