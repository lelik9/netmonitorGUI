package gui.view.table;

import gui.view.panel.ToolsPanel;

import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

public class AllTableModel extends AbstractTableModel
	{
		/**
	 * 
	 */
	private static final long serialVersionUID = 9184949332205401766L;
		private static int row;
		private static int column;
		
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
			//	getColumnName(arg1);
				String data = err.get(arg0+1);
				if(data == null){data = "0";}
				
				return data;

			}
		
		public String getColumnName()
			{
				return MyComboBoxModel.getName();
			}
	}
