package gui.view.table;

import gui.controller.NetController;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

class AllTableFrame extends JFrame
{
	private NetController netController;
	private int DEFAULT_WIDTH = 800;
	private int DEFAULT_HEIGHT = 600;
	private AllTableModel allTableModel;
	public  AllTableFrame(NetController netController)
			{
				
				setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
				TableModel model = new AllTableModel(Table.getRows(),Table.getColumns());
				
				JComboBox comboBox = new JComboBox(new MyComboBoxModel());	
				comboBox.addActionListener(actionListener);
				
				JPanel comboPanel = new JPanel();
				comboPanel.add(comboBox, BorderLayout.EAST );
				add(comboPanel, BorderLayout.NORTH);
				
				JTable table = new JTable(model);	
				table.setLayout(new BorderLayout());
				add(new JScrollPane(table), BorderLayout.CENTER);
				this.netController = netController;

				
			}
	ActionListener actionListener = new ActionListener() 
		{
	    public void actionPerformed(ActionEvent e) 
	    	{
	    		
	    		String selection = MyComboBoxModel.getName();
	    		System.out.println(selection);
	    		Table t = new Table(netController);

				try
					{

						Map<Integer, List<String>> devicesInfo = netController.getDeviceInfo("extintinfo", selection);
			    //		ToolsPanel.setDevicesInfo(devicesInfo);
				//		allTableModel.getValueAt(allTableModel.getRowCount(), allTableModel.getColumnCount());
						System.out.println(devicesInfo);
					//	t.setRows(1);
					//	t.setColumns(16);
					//	t.Table(netInteractionController);
					//	t.createFrame("Extended Interface Information");
						
					} catch (IOException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    	}
		};

}
