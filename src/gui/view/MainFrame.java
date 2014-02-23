package gui.view;

import gui.controller.NetInteractionController;
import gui.model.Server;
import gui.view.panel.FastAccessPanel;
import gui.view.panel.ToolsPanel;
import gui.view.panel.VisualPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MainFrame extends JFrame{

    private static final long serialVersionUID = 1L;

    private JMenuBar topMenuBar;
    
    private JPanel mainContentPanel;
    private ToolsPanel toolsPanel;
    private VisualPanel visualPanel;    
    private FastAccessPanel fastAccessPanel;
    
    private NetInteractionController netInteractionController;
    
    public MainFrame(){
	    initFrameCharacteristics();

	    // FIXME temporary solution
	    showLoginDialog();
	    serverConnect();
	    
	    initContextMenu();
	    initPannels();	 
	    
	    pack();
	}

	private void showLoginDialog() {
		JTextField username = new JTextField();
		JTextField password = new JPasswordField();
		Object[] message = {
		    "Username:", username,
		    "Password:", password
		};
		
		int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
		
		if (option == JOptionPane.OK_OPTION) {
			// FIXME check login procedure
		    if (username.getText().equals("test") && password.getText().equals("test")) {
		        System.out.println("Login successful");
		    } else {
		        JOptionPane.showMessageDialog(this, "Login failed!");
		        System.exit(0);
		    }
		}
	}
	
	private void serverConnect()
	{
		JTextField host = new JTextField();
		JTextField port = new JPasswordField();
		Object[] message = {
		    "Host:", host,
		    "Port:", port
		};
		
		int option = JOptionPane.showConfirmDialog(null, message, "Choose server", JOptionPane.OK_CANCEL_OPTION);
		
		if (option == JOptionPane.OK_OPTION) {
			Server server = new Server("151.237.240.15",9123);
		//	Server server = new Server(host.getText(), Integer.valueOf( port.getText() ) );
			netInteractionController = new NetInteractionController(server);			
		}
	}

	private void initPannels() {
	
		mainContentPanel = new JPanel();
		mainContentPanel.setLayout( new BorderLayout() );
		
		fastAccessPanel = new FastAccessPanel();
		mainContentPanel.add(fastAccessPanel, BorderLayout.BEFORE_FIRST_LINE);

		toolsPanel = new ToolsPanel(netInteractionController);
		mainContentPanel.add(toolsPanel, BorderLayout.LINE_START);
		
		visualPanel = new VisualPanel();
		mainContentPanel.add(visualPanel, BorderLayout.CENTER);
		
		setContentPane(mainContentPanel);
		setResizable(false);
	}

	private void initContextMenu() {
		topMenuBar = new JMenuBar();
		
		JMenu applicationMenu = new JMenu("Application");
		JMenuItem openItem = new JMenuItem("Open");
		JMenuItem setupServer = new JMenuItem("Setup server");
		JMenuItem closeItem = new JMenuItem("Close");
		
		applicationMenu.add(openItem);
		applicationMenu.add(setupServer);
		applicationMenu.add(closeItem);
		
	    
	/*    setupServer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				Server server = showChooseServerDialog();
				netInteractionController = new NetInteractionController(server);
			}
		});*/


		topMenuBar.add(applicationMenu);
		topMenuBar.add(applicationMenu);
		
		JMenu windowMenu = new JMenu("Window");
		topMenuBar.add(windowMenu);
		
		JMenu helpMenu = new JMenu("Help");
		
		JMenuItem documentationItem = new JMenuItem("Documentation");
		JMenuItem aboutItem = new JMenuItem("About");
		
		helpMenu.add(documentationItem);
		helpMenu.add(aboutItem);
		
		aboutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(MainFrame.this, "Net monitor application");
			}
		});
		
		topMenuBar.add(helpMenu);
		
		setJMenuBar(topMenuBar);

	}
/*	
	private Server showChooseServerDialog(){
		JTextField host = new JTextField();
		JTextField port = new JPasswordField();
		Object[] message = {
		    "Host:", host,
		    "Port:", port
		};
		
		int option = JOptionPane.showConfirmDialog(null, message, "Choose server", JOptionPane.OK_CANCEL_OPTION);
		
		if (option == JOptionPane.OK_OPTION) {
			Server server = new Server(host.getText(), Integer.valueOf( port.getText() ) );
			
			return server;
		}
		
		return null;
	}*/

	private void initFrameCharacteristics(){
    	setTitle("Net Monitor v0.0.1");
    	setSize(1024, 768);
    	
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args){
    	MainFrame mainFrame = new MainFrame();
    	mainFrame.setVisible(true);
    }
}
