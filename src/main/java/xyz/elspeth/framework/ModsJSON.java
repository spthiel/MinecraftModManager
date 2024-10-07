package xyz.elspeth.framework;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.lang.runtime.ObjectMethods;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class ModsJSON {
	
	private static final String FILE_NAME = "mods_lock.json";

	public static ModsJSON from(Path path) throws IOException {
		File file = path.resolve(FILE_NAME).toFile();
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(file, ModsJSON.class);
	}
	
	public void to(Path path) throws IOException {
		
		File file = path.resolve(FILE_NAME).toFile();
		
		ObjectMapper mapper = new ObjectMapper();
		
		mapper.writeValue(file, this);
	}
	
	public Version           version;
	public HashMap<String, Version> mods;
	
}
