package menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;

import frame.GPanel;
import main.GConstants.EGraphicsMenuItem;

public class GGraphicsMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	private GPanel panel;

	public GGraphicsMenu(String text) {
		super(text);
		
		ActionHandler actionHandler = new ActionHandler();
		for(EGraphicsMenuItem eGraphicsMenuItem: EGraphicsMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eGraphicsMenuItem.getText());
			menuItem.setActionCommand(eGraphicsMenuItem.name());
			menuItem.setToolTipText(eGraphicsMenuItem.getTip());
			menuItem.addActionListener(actionHandler);
			this.add(menuItem);
		}
	}
	public void setAssociation(GPanel panel) {
		this.panel = panel;		
	}
	private void setLineColor() {
		Color lineColor = JColorChooser.showDialog(this, "선 색", Color.black);
		if(lineColor==null) {
			lineColor=Color.black;
		}
		this.panel.setLineColor(lineColor);
	}
	private void setFillColor() {
		Color fillColor = JColorChooser.showDialog(this, "채우기 색", Color.white);
		if(fillColor==null) {
			fillColor=Color.white;
		}
		this.panel.setFillColor(fillColor);
	}
	private void setThickness() {
		String[] t = {"1","3","5","10"};
		Object selected = JOptionPane.showInputDialog(null, "두께를 고르세요", "선 굵기",
				JOptionPane.QUESTION_MESSAGE, null,t , t[0]);
		int lt = Integer.parseInt((String) selected);
		if(selected == null)
			this.panel.setThickness(1);
		else
			this.panel.setThickness(lt);
		
	}
	private class ActionHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			EGraphicsMenuItem eGraphicsMenuItem = EGraphicsMenuItem.valueOf(e.getActionCommand());
			switch(eGraphicsMenuItem) {
			case eLineColor:
				setLineColor();
				break;
			case eFillColor:
				setFillColor();
				break;
			case eThickness:
				setThickness();
				break;
			default:
				break;
			}
		
		}
		}
}
