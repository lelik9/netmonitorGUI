package gui.controller;

import gui.view.MainFrame;
import util.PropertyReader;

public class ApplicationController {

	private PropertyReader propertyReader; 
	
	private NetInteractionController netInteractionController;
	
	private MainFrame mainFrame;
	
	public ApplicationController() {
		propertyReader = new PropertyReader();
	}
	
	
	
}
