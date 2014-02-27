package gui.view.panel;

import gui.controller.NetInteractionController;
import gui.model.Server;
import gui.view.table.Table;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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

	private JButton networkButton = new JButton("Network");
	private JButton serversButton = new JButton("Servers");
	private JButton printersButton = new JButton("Printers");
	
	private String[] networkElement = {"Interface info", "Extended interface info", "Vlan table", "Mac address table", "Arp table"};
	private JList networkList = new JList(networkElement);
	
	private JButton troubleshootButton = new JButton("Troubleshoot");
	private JButton maintainceButton = new JButton("Maintaince");
	
	private JButton deviceInfoButton = new JButton("Device Info request");
		
	private NetInteractionController netInteractionController;
	
	public ToolsPanel(NetInteractionController netInteractionController) 
		{
		
			

		// workaround to fix cell size
		JPanel listPane = new JPanel();
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));

		networkList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		networkList.addMouseListener(mouseListener);
		
		add("Button 1", listPane);
		
		listPane.add(networkButton);
		networkButton.setAlignmentX(CENTER_ALIGNMENT);
		networkButton.setMaximumSize(new Dimension(200, 150));
		
		listPane.add(networkList);
		networkList.setVisible(false);

		
		listPane.add(serversButton);
		serversButton.setAlignmentX(CENTER_ALIGNMENT);
		serversButton.setMaximumSize(new Dimension(200, 150));
		
		listPane.add(printersButton);
		printersButton.setAlignmentX(CENTER_ALIGNMENT);
		printersButton.setMaximumSize(new Dimension(200, 150));
		
		// FIXME: ugly workaround, how to do it in proper way?
		this.netInteractionController = netInteractionController;
		
		setUpActions();
	}
	
	MouseListener mouseListener = new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e)
	    	{
	    		if (e.getClickCount() == 1) 
	        	{
	        		String selectedItem = (String) networkList.getSelectedValue();
	        	//	System.out.println(selectedItem);
	        		if(selectedItem.equals("Extended interface info"))
	        			{
	        				Table t = new Table();

	        					try
									{
										devicesInfo = netInteractionController.getDeviceInfo("extintinfo", "7_yarus");
			        					setDevicesInfo(devicesInfo);
			        					
			        					devicesName = netInteractionController.getDeviceName("devices", "0");
			        					setDevicesName(devicesName);
									} catch (IOException e1)
									{
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
	
	        					t.setRows(1);
	        					t.setColumns(16);
	        					t.Table(netInteractionController);
	        					
	        					t.createFrame("Extended Interface Information");	        			
	        			}
	        		if(selectedItem.equals("Interface info"))
	        			{
	        				Table t = new Table();

	        					try
									{
										devicesInfo = netInteractionController.getDeviceInfo("intinfo", "7_yarus");
			        					setDevicesInfo(devicesInfo);
			        					System.out.println(devicesInfo);
			        					devicesName = netInteractionController.getDeviceName("devices", "0");
			        					setDevicesName(devicesName);
			        					System.out.println(devicesName);
									} catch (IOException e1)
									{
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
	
	        					t.setRows(1);
	        					t.setColumns(6);
	        					t.Table(netInteractionController);
	        					
	        					t.createFrame("Interface Information");	        			
	        			}
	        	}
	};
	};

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
					t.Table(netInteractionController);
					
					t.createFrame("Extended Interface Information");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		networkButton.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{

					networkList.setVisible(true);
				}
			});
	}
		
		
}
