package gui.view.panel;

import gui.controller.NetInteractionController;
import gui.model.Server;
import gui.view.table.Table;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.yaml.snakeyaml.Yaml;

public class ToolsPanel extends JPanel {
	private static Map<Integer, List<String>> devicesInfo;
	private static Map<Integer, List<String>> devicesName;
	
	public static Map<Integer, List<String>> getDevicesName()
		{
				return devicesName;
		}

	public static void setDevicesName(Map<Integer, List<String>> devicesName)
		{
				ToolsPanel.devicesName = devicesName;
		}

	public static Map<Integer, List<String>> getDevicesInfo()
		{
			return devicesInfo;
		}

	public static void setDevicesInfo(Map<Integer, List<String>> devicesInfo)
		{
			ToolsPanel.devicesInfo = devicesInfo;
		}

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
				Table t = new Table();
				try {
					devicesInfo = netInteractionController.getDeviceInfo("extintinfo", "7_yarus");
					setDevicesInfo(devicesInfo);
					
					devicesName = netInteractionController.getDeviceName("devices", "0");
					setDevicesName(devicesName);
					
					t.setRows(1);
					t.setColumns(16);
					t.createFrame("Extended Interface Information");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		maintainceButton.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{

					try
						{
							devicesInfo = netInteractionController.getDeviceName("devices", "0");
							System.out.println(devicesInfo);
						} catch (IOException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			});
	}
	
}
