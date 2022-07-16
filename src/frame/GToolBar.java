package frame;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.ToolTipManager;

import main.GConstants.EShapeTool;

public class GToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;
	private GPanel panel;
	
	public GToolBar() {
		ActionHandler actionHandler = new ActionHandler();
		for(EShapeTool eShapeTool: EShapeTool.values()) { 
			ImageIcon shapeIcon = changeIcon(eShapeTool.getImg(), 30, 30);
			ImageIcon selectedShapeImgIcon = changeIcon(eShapeTool.getImg_c(), 30, 30);
			JButton button = new JButton(shapeIcon);
			button.setRolloverIcon(selectedShapeImgIcon);
			button.setToolTipText(eShapeTool.getText());
			button.setBorderPainted(false);
			button.setActionCommand(eShapeTool.toString());
			button.addActionListener(actionHandler);

			this.add(button);
		}
		ToolTipManager ttm =ToolTipManager.sharedInstance();
		ttm.setEnabled(true);
		ttm.setInitialDelay(0);
	}
	private ImageIcon changeIcon(String img, int i, int j) {
		ImageIcon icon = new ImageIcon(img);
		Image image = icon.getImage();
		Image changedimg = image.getScaledInstance(i, j, Image.SCALE_SMOOTH);
		return new ImageIcon(changedimg);
	}
	public void initialize() {
		((JButton)(this.getComponent(EShapeTool.eRectangle.ordinal()))).doClick(); 
	}
	public void setAssociation(GPanel panel) {
		this.panel = panel;
	}
	private class ActionHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			EShapeTool eShapeTool = EShapeTool.valueOf(event.getActionCommand());
			panel.setShapeTool(eShapeTool.getShapeTool());
		}
	
	}
	
}
