package gui.view.table;

import gui.view.panel.ToolsPanel;

import java.util.List;
import java.util.Map;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class MyComboBoxModel extends AbstractListModel implements ComboBoxModel 
	{
		private static String Name;
		
		Map<Integer, List<String>> devicesName = AllTableModel.getDeviceName();
		List<String> devName = devicesName.get(1);				
		List<String> Device = devName;

		String selection = null;


		public static String getName()
			{
					return Name;
			}

		public void setName(String name)
			{
					Name = name;
			}

		public Object getElementAt(int index) 
		  {
			  setName(Device.get(index));
			  return Device.get(index);
		  }

	    public int getSize() 
	    	{
	    		return Device.size();
	    	}

	    public void setSelectedItem(Object anItem) 
		  {
			  selection = (String) anItem; // to select and register an
		  } // item from the pull-down list

	  // Methods implemented from the interface ComboBoxModel
	    public Object getSelectedItem() 
		  {
			  return selection; // to add the selection to the combo box
		  }
	
	}
