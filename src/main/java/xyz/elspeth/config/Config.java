package xyz.elspeth.config;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vdurmont.semver4j.Semver;

public class Config {

	public static Semver CURRENT_VERSION = new Semver("1.0.0");
	
	@JsonSerialize(converter = SemverConverter.class)
	public Semver VERSION        = new Semver("0.0.0");
	public String modrinthAPIKey = "apikey";
	public String newProperty = "newProperty";
	
}
