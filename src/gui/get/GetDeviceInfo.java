package gui.get;

import gui.controller.NetController;
import gui.view.table.AllTableModel;
import gui.view.table.Table;

public class GetDeviceInfo 
{
	private NetController netController;
	private static String function;
	
	public static String getFunction() {
		return function;
	}

	public static void setFunction(String function) {
		GetDeviceInfo.function = function;
	}

	public void GetController(NetController netController)
	{
		this.netController = netController;
	}
	
	public void GetDeviceInfo(String function, String device, String FrameName, int col)
	{
		Table t = new Table(netController);
		AllTableModel.setDeviceInfo(netController.getDeviceInfo(function, device));
		setFunction(function);

		AllTableModel.setDeviceName(netController.getDeviceName("devices", "no"));


		t.setRows(1);
		t.setColumns(col);
		
		t.createFrame(FrameName);	
	}
}
