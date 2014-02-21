package gui.model;

public class Server {

	private String host;
	private Integer port;
	
	public Server(String host, Integer port) {
		super();
		this.host = host;
		this.port = port;
	}

	public String getHost() {
		return host;
	}
	
	public Integer getPort() {
		return port;
	}
	
}
