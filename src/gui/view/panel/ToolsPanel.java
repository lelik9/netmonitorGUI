package gui.view.panel;

import gui.controller.NetInteractionController;
import gui.model.Server;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ToolsPanel extends JPanel {

	private static final long serialVersionUID = 5802953052002312498L;

	private JButton setupButton = new JButton("Setup");
	private JButton configureButton = new JButton("Configure");
	private JButton monitorButton = new JButton("Monitor");
	private JButton troubleshootButton = new JButton("Troubleshoot");
	private JButton maintainceButton = new JButton("Maintaince");
	
	private JButton deviceInfoButton = new JButton("Device Info request");
		
	private NetInteractionController netInteractionController;
	
	public ToolsPanel(NetInteractionController netInteractionController) {
		
    	add(setupButton);
		add(configureButton);
		add(monitorButton);
		add(troubleshootButton);
		add(maintainceButton);
		add(deviceInfoButton);

		// workaround to fix cell size
		GridLayout gridLayout = new GridLayout(10, 1);
		setLayout(gridLayout);
		
		// FIXME: ugly workaround, how to do it in proper way?
		this.netInteractionController = netInteractionController;
		
		setUpActions();
	}

	private void setUpActions() 
	{
		deviceInfoButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				try {
					String devicesInfo = netInteractionController.getDeviceInfo("7_yarus");
					JOptionPane.showMessageDialog(ToolsPanel.this, devicesInfo);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
}
