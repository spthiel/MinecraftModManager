package xyz.elspeth.printing;

import com.diogonunes.jcolor.AnsiFormat;
import com.diogonunes.jcolor.Attribute;

public enum Formats {

	INFO(),
	SUCCESS(Attribute.BRIGHT_GREEN_TEXT()),
	WARNING(Attribute.BRIGHT_YELLOW_TEXT()),
	ERROR(Attribute.BRIGHT_RED_TEXT()),
	;
	
	private final AnsiFormat ansiFormat;
	
	Formats(Attribute... formats) {
		this(new AnsiFormat(formats));
	}
	
	Formats(AnsiFormat ansiFormat) {
		this.ansiFormat = ansiFormat;
	}
	
	public String colorize(String text) {
		return ansiFormat.format(text);
	}
}
