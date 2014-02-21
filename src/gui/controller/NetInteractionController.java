package gui.controller;

import gui.model.Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import util.YAMLConverter;

public class NetInteractionController {

	private int serverPort;
	private String serverHost;
	
	private static final String ECHO_MESSAGE = "echo";
	private static final String ECHO_OUTPUT = "ohce";
	
	private Socket socket;
	
	private YAMLConverter yamlConverter;
	
	public NetInteractionController(String serverHost, int serverPort) {
		this.serverHost = serverHost;
		this.serverPort = serverPort;
		
		yamlConverter = new YAMLConverter();
		
		establishConnection();
	}
	
	public NetInteractionController(Server server) {
		this(server.getHost(), server.getPort());
	}
	
	
	private void establishConnection(){
		try {
			InetAddress address = InetAddress.getByName(this.serverHost);
			
			socket = new Socket(address, serverPort);
		} catch (UnknownHostException e) {
		    // FIXME add logging
			e.printStackTrace();
		} catch (IOException e) {
			// FIXME add logging
			e.printStackTrace();
		}
	}
	
	public boolean testConnection() throws IOException{
		sendMessageToServer(ECHO_MESSAGE);
		
		String result = receiveMessageFromServer();
		
		return result.equals(ECHO_OUTPUT);
	}
	
	public String getDeviceInfo(String deviceName) throws IOException
	{
	
		String request = yamlConverter.deviceToNameRequest(deviceName);
		sendMessageToServer(request);
		return receiveMessageFromServer(); 
		
	}

	private String receiveMessageFromServer() throws IOException{
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String message = br.readLine();

		is.close();
		
		return message;
	}
		
	private void sendMessageToServer(String message) throws IOException{
		OutputStream os = socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os);
		
		BufferedWriter bufferedWriter = new BufferedWriter(osw);
		
		bufferedWriter.write(message);
		bufferedWriter.flush();	
		
		//os.close();
	}
	
	public void test()
	{
		System.out.println("WORK");
	}
		
	public int getServerPort() {
		return serverPort;
	}

	public String getServerHost() {
		return serverHost;
	}
	
}
