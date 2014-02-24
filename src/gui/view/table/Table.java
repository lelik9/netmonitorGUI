package gui.view.table;



import gui.view.panel.ToolsPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class Table extends JFrame
{
	private static final int DEFAULT_WIDTH = 640;
	private static final int DEFAULT_HEIGHT = 480;
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
		public void createFrame()
			{
				JFrame frame = new AllTableFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
class AllTableFrame extends JFrame
{
			public  AllTableFrame()
				{
					
					setTitle("Extended Interface Indo");
					setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
					
					TableModel model = new AllTableModel(getRows(),getColumns());		
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
			public Object getValueAt(int arg0, int arg1)
				{
					// TODO Auto-generated method stub
					Map<String, List<String>> device = ToolsPanel.getDevicesInfo();
	
				//	List<String> col = device.get(arg1);

					List<String> err = device.get(arg1);

					return err.get(arg0+1);

				}
			
			public String getColumnName()
				{
					return getName();
				}
			
		}
}
