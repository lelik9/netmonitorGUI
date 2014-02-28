package gui.view.table;

import javax.swing.JFrame;
import gui.controller.NetController;

public class Table extends JFrame
{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7530665374631112442L;
	private static int rows;
	private static int columns;
	private static String Name;
	
	private NetController netController;

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
		
	public Table(NetController netController)
		{
			this.netController = netController;
		}
	
		public void createFrame(String title)
			{
				
				JFrame frame = new AllTableFrame(netController);
				frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				frame.setVisible(true);
				frame.setTitle(title);
				System.out.println(netController);

			}
		

		
}
