package frame;

import javax.swing.JMenuBar;

import main.GConstants.EMenu;
import menu.GEditMenu;
import menu.GFileMenu;
import menu.GGraphicsMenu;

public class GMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	public String fileName;
	
	private GPanel panel;
	private GFileMenu fileMenu;
	private GEditMenu editMenu;
	private GGraphicsMenu graphicsMenu;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName() {
		this.fileName = fileMenu.getFileName();
	}
	
	public GMenuBar() {
		this.fileMenu = new GFileMenu(EMenu.eFile.getText());
		this.add(this.fileMenu);
		
		this.fileName = fileMenu.getFileName();
		
		this.editMenu = new GEditMenu(EMenu.eEdit.getText());
		this.add(this.editMenu);
		
		this.graphicsMenu = new GGraphicsMenu(EMenu.eGraphics.getText());
		this.add(this.graphicsMenu);
	}

	public void setAssociation(GPanel panel) {	
		this.fileMenu.setAssociation(panel);
		this.editMenu.setAssociation(panel);
		this.graphicsMenu.setAssociation(panel);
	}

}
