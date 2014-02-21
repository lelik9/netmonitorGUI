package gui.view.panel;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class FastAccessPanel extends JPanel{

	private static final long serialVersionUID = -1679479002201555998L;

	// Actually 32x32 - 2px for hide border
	private static final Dimension ICON_SIZE = new Dimension(30, 30);
	
	private JButton saveButton;
	private JButton loadButton;
	private JButton configureButton;	
	
	public FastAccessPanel() {
		initButtons();
		
		setLayout(new FlowLayout(FlowLayout.LEFT));		
	}

	private void initButtons(){
		
	    ImageIcon loadButtonIcon = createImageIcon("resources/images/load.png");
	    ImageIcon saveButtonIcon = createImageIcon("resources/images/save.jpeg");
	    ImageIcon configureButtonIcon = createImageIcon("resources/images/config.png");
			    
		saveButton = new JButton();
		saveButton.setSize(ICON_SIZE);
		saveButton.setIcon(loadButtonIcon);
		add(saveButton);
		
		loadButton = new JButton();
		loadButton.setSize(ICON_SIZE);
		loadButton.setIcon(saveButtonIcon);
		add(loadButton);
		
		configureButton = new JButton();
		configureButton.setSize(ICON_SIZE);
		configureButton.setIcon(configureButtonIcon);
		add(configureButton);
	}


	protected static ImageIcon createImageIcon(String path) {
		if (path != null) {
			return new ImageIcon(path);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
}
