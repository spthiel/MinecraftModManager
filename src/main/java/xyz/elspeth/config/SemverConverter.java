package xyz.elspeth.config;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import com.vdurmont.semver4j.Semver;

public class SemverConverter implements Converter<Semver, String> {
	
	@Override
	public String convert(Semver semver) {
		
		return semver.toString();
	}
	
	@Override
	public JavaType getInputType(TypeFactory typeFactory) {
		
		return typeFactory.constructType(Semver.class);
	}
	
	@Override
	public JavaType getOutputType(TypeFactory typeFactory) {
		
		return typeFactory.constructType(String.class);
	}
}
