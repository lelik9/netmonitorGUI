package gui.view.table;



import gui.controller.NetInteractionController;
import gui.view.panel.ToolsPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class Table extends JFrame
{


	private static int rows;
	private static int columns;
	private static String Name;
	
	private NetInteractionController netInteractionController;

		public String getName()
		{
			return Name;
		}
	public void setName(String name)
		{
			Name = name;
		}
		public static int getRows()
		{
			return rows;
		}
	public static void setRows(int rows)
		{
			Table.rows = rows;
		}
	public static int getColumns()
		{
			return columns;
		}
	public static void setColumns(int columns)
		{
			Table.columns = columns;
		}
		
	public void Table(NetInteractionController netInteractionController)
		{
			this.netInteractionController = netInteractionController;
		}
	
		public void createFrame(String title)
			{
				
				JFrame frame = new AllTableFrame(netInteractionController);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				frame.setTitle(title);
				System.out.println(netInteractionController);

			}
		

		
}
