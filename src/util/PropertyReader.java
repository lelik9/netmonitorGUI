package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

	private static final String PROPERTY_FILE_PATH = "resources/netmonitor.properties";
	
	// properties list
	private static final String SERVER_HOST = "server_host";
	private static final String SERVER_PORT = "server_port";
	
	private Properties properties;
	
	public PropertyReader() {
	
		properties = new Properties();
		
		File file = new File(PROPERTY_FILE_PATH);

		loadProperties(file);
	}
	
	private void loadProperties(File propertyFile){
		try {
			properties.load(new FileInputStream(propertyFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getServerHost(){
		if (properties == null){
			return null;
		}
		
		return properties.getProperty(SERVER_HOST);
	}
	
	public int getServerPort(){
		if (properties == null){
			return -1;
		}
		
		String serverPortString = properties.getProperty(SERVER_PORT);
		int serverPort = Integer.valueOf(serverPortString);
		
		if (serverPort < 0 || serverPort > 65535){
			return -1;
		}
		
		return serverPort;
	}
	
}