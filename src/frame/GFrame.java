package frame;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import main.GConstants;

public class GFrame extends JFrame {
	//attributes
	private static final long serialVersionUID = 1L;
	
	//components
	private GMenuBar menuBar;
	private GToolBar toolBar;
	private GPanel panel; 
	private GFooterPanel footerPanel;

	public GFrame() { 
		//initialize attributes 
		this.setTitle("±×¸²ÆÇ");
		this.setLocation(GConstants.CFrame.point);
		this.setSize(GConstants.CFrame.dimension);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//initialize components 
		this.menuBar = new GMenuBar();
		this.setJMenuBar(this.menuBar); 
		
		BorderLayout layoutManager = new BorderLayout();
		this.getContentPane().setLayout(layoutManager);
		
		this.toolBar = new GToolBar();
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		this.panel = new GPanel();
		this.getContentPane().add(panel, BorderLayout.CENTER);
		
		this.footerPanel = new GFooterPanel();
		this.getContentPane().add(footerPanel, BorderLayout.SOUTH);
	
		//set associations
		this.menuBar.setAssociation(this.panel);
		this.toolBar.setAssociation(this.panel);
		this.panel.setAssociation(this.menuBar, this.footerPanel);
	}

	public void initialize() { 
		this.toolBar.initialize();
		this.panel.initialize();
		this.footerPanel.initialize();
	
	}
}
