package gui.view.table;



import gui.view.panel.ToolsPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import java.util.Map;

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
	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 600;
	private int row;
	private int column;
	private static int rows;
	private static int columns;
	private static String Name;

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
		public void createFrame(String title)
			{
				JFrame frame = new AllTableFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				frame.setTitle(title);
			}
class AllTableFrame extends JFrame
{
			public  AllTableFrame()
				{
					
					//setTitle("Extended Interface Info");
					setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
					String[] items = {
				            "Ёлемент списка 1",
				            "Ёлемент списка 2",
				            "Ёлемент списка 3"
				        };
					Map<Integer, List<String>> devicesName = ToolsPanel.getDevicesName();
				//	String[] items = devicesName.get(1);
					TableModel model = new AllTableModel(getRows(),getColumns());
					JComboBox comboBox = new JComboBox(items);
					JPanel comboPanel = new JPanel();
					comboPanel.add(comboBox);
					add(comboPanel, BorderLayout.NORTH);
					JTable table = new JTable(model);	
					add(new JScrollPane(table));
					
				}
		
}
		class AllTableModel extends AbstractTableModel
		{
			
			public AllTableModel(int rows, int columns)
				{
					row = rows;
					column = columns;
				}

			@Override
			public int getColumnCount()
				{
					// TODO Auto-generated method stub
					return column;
				}

			@Override
			public int getRowCount()
				{
					// TODO Auto-generated method stub
					return row;
				}

		    @Override
		    public String getColumnName(int index) 
		    	{
		    		Map<Integer, List<String>> device = ToolsPanel.getDevicesInfo();
		    		List<String> err = device.get(index);
		    		return err.get(0);
		    	}
		    
			@Override
			public Object getValueAt(int arg0, int arg1)
				{
					// TODO Auto-generated method stub
					Map<Integer, List<String>> device = ToolsPanel.getDevicesInfo();
	
					List<String> err = device.get(arg1);
					row = err.size()-1;
					getColumnName(arg1);
					String data = err.get(arg0+1);
					if(data == null){data = "0";}
					
					return data;

				}
			
			public String getColumnName()
				{
					return getName();
				}
			
		}
}
