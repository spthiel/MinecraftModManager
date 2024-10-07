package xyz.elspeth.framework;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class ModsLockJSON {
	
	private static final String FILE_NAME = "mods_lock.json";
	
	public static ModsJSON from(Path path) throws IOException {
		
		File file = path.resolve(FILE_NAME)
						.toFile();
		ObjectMapper mapper = new ObjectMapper();
		
		return mapper.readValue(file, ModsJSON.class);
	}
	
	public void to(Path path) throws IOException {
		
		File file = path.resolve(FILE_NAME).toFile();
		
		ObjectMapper mapper = new ObjectMapper();
		
		mapper.writeValue(file, this);
	}
	
	public Version                     version;
	public Version                     lockfileVersion;
	public HashMap<String, Dependency> mods;
	
	public static class Dependency {
		
		public String                   name;
		public Version                  version;
		public URL                      resolved;
		public HashMap<String, Version> dependencies;
	}
}
