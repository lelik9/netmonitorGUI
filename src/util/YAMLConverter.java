package util;

import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class YAMLConverter {

	private Yaml yaml;
	
	public YAMLConverter() {
		yaml = new Yaml();
	}
	
	public String deviceToNameRequest(String deviceName, String func){
		Map<String, Object> data = new HashMap<String, Object>();
		
		data.put("func", func);
		data.put("device", deviceName);
		
		String output = yaml.dump(data);
		return output;
	}
	
	public Map<String, Object> responceToDeviceInfo(String responce){
		return (Map<String, Object>) yaml.load(responce);
	}
	
}
