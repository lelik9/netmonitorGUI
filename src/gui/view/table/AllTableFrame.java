package gui.view.table;

import gui.controller.NetController;
import gui.view.panel.ToolsPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

class AllTableFrame extends JFrame {
	private NetController netController;
	private int DEFAULT_WIDTH = 800;
	private int DEFAULT_HEIGHT = 600;
	private AllTableModel allTableModel;

	public AllTableFrame(final NetController netController) {

		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		TableModel model = new AllTableModel(Table.getRows(),
				Table.getColumns());
		
		JPanel comboPanel = new JPanel();


		final JTable table = new JTable(model);
		table.setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);

		final JComboBox comboBox = new JComboBox(new MyComboBoxModel());
		comboBox.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String selection = MyComboBoxModel.getName();
						comboBox.setSelectedItem(selection);
						table.updateUI();					
						System.out.println("Selection is " + selection);
						//Table t = new Table(netController);

						Map<Integer, List<String>> devicesInfo = netController.getDeviceInfo("extintinfo", selection);
						
						ToolsPanel.setDevicesInfo(devicesInfo);
						
						allTableModel.getValueAt(allTableModel.getRowCount(), 
								allTableModel.getColumnCount());
						//t.setRows(1);
						//t.setColumns(16);
						//t.createFrame("Extended Interface Information");
						
					}
				});

		comboPanel.add(comboBox, BorderLayout.EAST);
		add(comboPanel, BorderLayout.NORTH);
		this.netController = netController;

	}
}
