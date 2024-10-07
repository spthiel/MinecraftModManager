package xyz.elspeth.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

public class HTTP {
	
	private final HashMap<String, String> headers;
	
	public HTTP(HashMap<String, String> defaultHeaders) {
		this.headers = defaultHeaders;
	}
	
	private HttpURLConnection createConnection(URL url) throws IOException {
		var connection = (HttpURLConnection) url.openConnection();
		
		// Set user agent for connection
		connection.setRequestProperty("User-Agent", "minecraft-mod-manager");
		
		this.headers.forEach(connection :: setRequestProperty);
		
		return connection;
	}

	public String get(String url) throws IOException, URISyntaxException {
		var urlObject = new URI(url).toURL();
		
		var connection = createConnection(urlObject);
		
		connection.setRequestMethod("GET");
		connection.connect();
		
		var status = connection.getResponseCode();
		
		if (status > 299) {
			throw new IOException("Invalid response code: %d".formatted(status));
		}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String        inputLine;
		StringBuilder content = new StringBuilder();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();
		
		return content.toString();
	}

}
