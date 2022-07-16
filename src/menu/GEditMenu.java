package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import frame.GPanel;
import main.GConstants.EEditMenuItem;

public class GEditMenu extends JMenu {
	private static final long serialVersionUID = 1L;

	private GPanel panel;

	public GEditMenu(String text) {
		super(text);

		ActionHandler actionHandler = new ActionHandler(); 
		for(EEditMenuItem eEditMenuItem: EEditMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eEditMenuItem.getText());
			menuItem.setActionCommand(eEditMenuItem.name());
			menuItem.setToolTipText(eEditMenuItem.getTip());
			menuItem.addActionListener(actionHandler);
			this.add(menuItem);
		}
	}
	public void setAssociation(GPanel panel) {
		this.panel = panel;		
	}

	private void undo() {
		this.panel.undo();
	}
	private void redo() {
		this.panel.redo();
	}
	private void copy() {
		this.panel.copy();
	}
	private void paste() {
		this.panel.paste();
	}
	private void cut() {
		this.panel.cut();
	}
	private void delete() {
		this.panel.delete();
	}
	private void deleteAll() {
		this.panel.deleteAll();
	}
	private void front() {
		this.panel.front();
	}
	private void back() {
		this.panel.back();
	}
	private class ActionHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			EEditMenuItem eEditMenuItem = EEditMenuItem.valueOf(e.getActionCommand());
			switch(eEditMenuItem) {
			case eUndo:
				undo();
				break;
			case eRedo:
				redo();
				break;
			case eCut:
				cut();
				break;
			case eCopy:
				copy();
				break;
			case ePaste:
				paste();
				break;
			case eDelete:
				delete();
				break;
			case eDeleteAll:
				deleteAll();
				break;
			case eFront:
				front();
				break;
			case eBack:
				back();
				break;
			default:
				break;
			}
		}
	}
}
