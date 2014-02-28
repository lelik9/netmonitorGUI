package gui.controller;

import gui.model.Server;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;
import util.YAMLConverter;

public class NetController {
	
	private YAMLConverter yamlConverter;
	private Yaml yaml;
	private NetInteractionController netInteractionController;
	
	public NetController(Server server) {
		yaml = new Yaml();
		yamlConverter = new YAMLConverter();
		netInteractionController = new NetInteractionController(server);		
	}
	
	
	public Map<Integer, List<String>> getDeviceName(String deviceName, String func) 
	{
		return getInfo(deviceName, func); 
	}
	
	public Map<Integer, List<String>> getDeviceInfo(String deviceName, String func) 
	{
		return getInfo(deviceName, func); 
	}
	
	private Map<Integer, List<String>> getInfo(String deviceName, String func) 
	{
		String request = yamlConverter.deviceToNameRequest(func, deviceName);
		System.out.println("sended " + request);
		netInteractionController.sendMessageToServer(request);
		Map<Integer, List <String>> data = new HashMap<Integer, List<String>>() ;
		String message = netInteractionController.receiveMessageFromServer();
		System.out.println("received " + message);
		data.putAll((Map<Integer, List<String>>) yaml.load(message));			
		
		return data; 
	}

}
