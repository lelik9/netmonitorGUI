package gui.view.table;

import gui.controller.NetController;
import gui.get.GetDeviceInfo;
import gui.view.panel.ToolsPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

class AllTableFrame extends JFrame {
	

	private int DEFAULT_WIDTH = 800;
	private int DEFAULT_HEIGHT = 600;
	private static String function;
	
	public AllTableFrame(final NetController netController) {

		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		TableModel model = new AllTableModel(Table.getRows(),
				Table.getColumns());
		
		JPanel comboPanel = new JPanel();
		
		JComboBox comboBox = new JComboBox(new MyComboBoxModel());
		comboBox.setSelectedItem(netController.getDeviceName());
		add(comboPanel, BorderLayout.NORTH);
		comboPanel.add(comboBox, BorderLayout.EAST);
		JTable table = new JTable(model);
		table.setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);


		comboBox.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						String selection = MyComboBoxModel.getName();
						function = GetDeviceInfo.getFunction();
						Map<Integer, List<String>> devicesInfo = netController.getDeviceInfo(function, selection);
						
						AllTableModel.setDeviceInfo(devicesInfo);;
						
					}
				});


	}
}
