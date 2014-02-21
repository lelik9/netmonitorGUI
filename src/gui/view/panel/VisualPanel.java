package gui.view.panel;

import javax.swing.JLabel;
import javax.swing.JPanel;

import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.assignment.DataColorAction;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.activity.Activity;
import prefuse.data.Graph;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.LabelRenderer;
import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;

public class VisualPanel extends JPanel {

	private static final long serialVersionUID = 5769675574292340925L;

	private Graph netGraph;
	
	public VisualPanel() {		
		Display visualDisplay = initGraphView();
		add(visualDisplay);
	}

	private Display initGraphView() {
		
		netGraph = new Graph();
		
		// FIXME stubbed data
		for ( int i = 0; i < 7; ++i ) {
           netGraph.addNode();
        }
		
		netGraph.addEdge(0, 3);
		netGraph.addEdge(3, 6);
		netGraph.addEdge(6, 0);
        //
		
		Visualization vis = new Visualization();
		vis.add("graph", netGraph);
        
		LabelRenderer labelRender = new LabelRenderer("labelRender");
		labelRender.setRoundedCorner(8, 8);
		
		vis.setRendererFactory(new DefaultRendererFactory(labelRender));
		
		int[] palette = new int[] {
		    ColorLib.rgb(255,180,180), ColorLib.rgb(190,190,255)
		};

		DataColorAction fill = new DataColorAction("graph.nodes", "gender",
		    Constants.NOMINAL, VisualItem.FILLCOLOR, palette);

		ColorAction text = new ColorAction("graph.nodes",
		    VisualItem.TEXTCOLOR, ColorLib.gray(0));

		ColorAction edges = new ColorAction("graph.edges",
		    VisualItem.STROKECOLOR, ColorLib.gray(200));
			
		ActionList color = new ActionList();
		color.add(fill);
		color.add(text);
		color.add(edges);
		
		ActionList layout = new ActionList(Activity.INFINITY);
		layout.add(new ForceDirectedLayout("graph"));
		layout.add(new RepaintAction());

		vis.putAction("color", color);
		vis.putAction("layout", layout);
		
		Display display = new Display(vis);
		display.setSize(650, 470);
	
		vis.run("color");
		vis.run("layout"); 
		
		return display;
	}
	

	
}
