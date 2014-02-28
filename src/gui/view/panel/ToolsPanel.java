package gui.view.panel;

import gui.controller.NetController;
import gui.get.GetDeviceInfo;
import gui.view.table.AllTableModel;
import gui.view.table.Table;

import java.awt.Dimension;
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
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;


public class ToolsPanel extends JPanel {


	private static final long serialVersionUID = 5802953052002312498L;

	private JButton networkButton = new JButton("Network");
	private JButton serversButton = new JButton("Servers");
	private JButton printersButton = new JButton("Printers");
	
	private String[] networkElement = {"Interface info", "Extended interface info", "Vlan table", "Mac address table", "Arp table"};
	private JList networkList = new JList(networkElement);
	
		
	private NetController netController;
	private GetDeviceInfo getDeviceInfo;
	
	
	public ToolsPanel(NetController netController) 
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
		this.netController = netController;
		
		getDeviceInfo = new GetDeviceInfo();
		getDeviceInfo.GetController(netController);
		
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
	        				getDeviceInfo.GetDeviceInfo("extintinfo", "7_yarus", "Extended interface information", 16);
	        			}
	        		if(selectedItem.equals("Interface info"))
	        			{
	        				getDeviceInfo.GetDeviceInfo("intinfo", "7_yarus", "Interface information", 6);        			
	        			}
	        		if(selectedItem.equals("Vlan table"))
        				{	
        				getDeviceInfo.GetDeviceInfo("vlantable", "7_yarus", "Vlan table", 2);        			
        				}
	        		if(selectedItem.equals("Mac address table"))
        				{
        				getDeviceInfo.GetDeviceInfo("mactable", "7_yarus", "Mac address table", 3);        			
        				}
	        		if(selectedItem.equals("Arp table"))
        				{
        				getDeviceInfo.GetDeviceInfo("arptable", "7_yarus", "Arp table", 3);        			
        				}
	        	}
	};
	};

	private void setUpActions() 
	{
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
