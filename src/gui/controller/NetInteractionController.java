package gui.controller;

import gui.model.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class NetInteractionController {

	private int serverPort;
	private String serverHost;
	
	private InputStream is;
	private InputStreamReader isr;
	private BufferedReader br;
    
	private PrintWriter printWriter;
	
	private static final String ECHO_MESSAGE = "echo";
	private static final String ECHO_OUTPUT = "ohce";
	
	private Socket socket;
	
	public NetInteractionController(String serverHost, int serverPort) {
		this.serverHost = serverHost;
		this.serverPort = serverPort;
		
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
		
		try {
			is = socket.getInputStream();
		} catch (IOException e) {
			System.out.println("Socket in is broken!");
			e.printStackTrace();
		}
		
		isr = new InputStreamReader(is);
	    br = new BufferedReader(isr);
	    	
		try {
			printWriter = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			System.out.println("Socket out is broken!");
		}
	}
	
	public boolean testConnection() throws IOException{
		sendMessageToServer(ECHO_MESSAGE);
		
		String result = receiveMessageFromServer();
		
		return result.equals(ECHO_OUTPUT);
	}
	

	public String receiveMessageFromServer() {
			
	    String message;
	    String returnMessage = "{";

		try {
			while(((message = br.readLine()) != null) && !("".equals(message)))
				{
					returnMessage = returnMessage.concat(message + ",");		
				}
		} catch (IOException e) {
			System.out.println("BufferReader is broken!");
			e.printStackTrace();
		}

		return returnMessage + "}";
	}
		
	public void sendMessageToServer(String message) {
				
		printWriter.println(message);
	}
			
	public int getServerPort() {
		return serverPort;
	}

	public String getServerHost() {
		return serverHost;
	}
	
}
