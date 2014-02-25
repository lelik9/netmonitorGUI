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
	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 600;
	private int row;
	private int column;
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
		public void createFrame(String title)
			{
				JFrame frame = new AllTableFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				frame.setTitle(title);
			}
class AllTableFrame extends JFrame
{
			private NetInteractionController netInteractionController;

			public  AllTableFrame()
				{
					
					//setTitle("Extended Interface Info");
					setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
					TableModel model = new AllTableModel(getRows(),getColumns());
					
					JComboBox comboBox = new JComboBox(new MyComboBoxModel());
					comboBox.addActionListener(actionListener);
					
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
		class MyComboBoxModel extends AbstractListModel implements ComboBoxModel 
		{
				Map<Integer, List<String>> devicesName = ToolsPanel.getDevicesName();
				List<String> devName = devicesName.get(1);				
				List<String> Device = devName;

			  String selection = null;

			  public Object getElementAt(int index) 
				  {
					  setName(Device.get(index));
					 return Device.get(index);
				  }

			  public int getSize() {
			    return Device.size();
			  }

			  public void setSelectedItem(Object anItem) {
			    selection = (String) anItem; // to select and register an
			  } // item from the pull-down list

			  // Methods implemented from the interface ComboBoxModel
			  public Object getSelectedItem() {
			    return selection; // to add the selection to the combo box
			  }
			}
		

		ActionListener actionListener = new ActionListener() 
			{
            public void actionPerformed(ActionEvent e) 
            	{
            		
            		String selection = getName();
            		System.out.println("1");
					//devicesInfo = netInteractionController.getDeviceInfo("extintinfo", "7_yarus");
            	}
			};
}
